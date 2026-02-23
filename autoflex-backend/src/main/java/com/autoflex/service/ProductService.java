package com.autoflex.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autoflex.dto.ProductMaterialDTO;
import com.autoflex.dto.ProductWithMaterialsDTO;
import com.autoflex.entity.Product;
import com.autoflex.entity.ProductRawMaterial;
import com.autoflex.repository.ProductRawMaterialRepository;
import com.autoflex.repository.ProductRepository;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;;

@ApplicationScoped
public class ProductService {
	
	@Inject
	ProductRepository productRepository;
	
	@Inject
	ProductRawMaterialRepository prmRepository;
	
	public List<ProductWithMaterialsDTO> listProductsWithMaterials() {

        List<Product> products = productRepository.listAll();
        List<ProductWithMaterialsDTO> result = new ArrayList<>();
        
        List<ProductRawMaterial> allRelations = prmRepository.listAll();
        
        Map<Long, List<ProductRawMaterial>> grouped =
        	    allRelations.stream()
        	        .collect(Collectors.groupingBy(prm -> prm.product.id));
        

        for (Product product : products) {

        	List<ProductRawMaterial> relations =
        			grouped.getOrDefault(product.id, List.of());

            ProductWithMaterialsDTO dto = new ProductWithMaterialsDTO();
            dto.id = product.id;
            dto.name = product.name;
            dto.price = product.price;

            dto.rawMaterialQuantity = relations.stream()
            		.map(prm -> prm.requireQuantity == null ? 0 : prm.requireQuantity)
            	    .reduce(0, Integer::sum);

            dto.materials = relations.stream().map(prm -> {
                ProductMaterialDTO m = new ProductMaterialDTO();
                m.productRawMaterialId = prm.id;
                m.rawMaterialId = prm.rawMaterial.id;
                m.name = prm.rawMaterial.name;
                m.requiredQty = prm.requireQuantity;
                return m;
            }).toList();

            result.add(dto);
        }

        return result;
    } 

	
	public List<Product> list(){
		return productRepository.listAll();
	}
	
	public Product findById(Long id) {
		Product product = productRepository.findById(id);
		if (product == null) {
	        throw new NotFoundException("Product not found");
	    }
		return product;
	}
	
	@Transactional
	public Product create (Product product) {
		if (product.name == null || product.name.isBlank()) {
	        throw new WebApplicationException("Product name is required", 400);
	    }

	    if (product.price == null || product.price.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new WebApplicationException("Product price must be greater than zero", 400);
	    }
		productRepository.persist(product);
		return product;
	}
	
	@Transactional
	public Product update (Long  id, Product product) {
		Product entity = productRepository.findById(id);
		if (entity == null) {
			throw new NotFoundException("Product not found");
		}
		
		if (product.price != null && product.price.compareTo(BigDecimal.ZERO) <= 0) {
		    throw new WebApplicationException("Price must be greater than zero", 400);
		}
		
		if (product.name != null) {
			entity.name = product.name;			
		}
		
		if (product.price != null) {
	        entity.price = product.price;
	    }
		entity.price = product.price;
		
		return entity;
		
	}
	
	@Transactional
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        
        try {
        	
        	Long relationsDeleted  = prmRepository.deleteByProductId(productId);  
        	Log.info(
        		    "Deleted " + relationsDeleted + " raw material relations for product " + productId
        		);


        	boolean deleted  = productRepository.deleteById(productId);
			
        	if (!deleted) {
        	    throw new WebApplicationException("Unable to delete product", 409);
        	}
        	
		} catch (Exception e) {
		    Log.error("Error deleting product {}", productId, e);
		    throw new WebApplicationException("Unable to delete product", 409);
		}
    }
}

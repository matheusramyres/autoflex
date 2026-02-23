package com.autoflex.service;

import java.util.List;

import com.autoflex.dto.ProductRawMaterialDTO;
import com.autoflex.entity.Product;
import com.autoflex.entity.ProductRawMaterial;
import com.autoflex.entity.RawMaterial;
import com.autoflex.repository.ProductRawMaterialRepository;
import com.autoflex.repository.ProductRepository;
import com.autoflex.repository.RawMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class ProductRawMaterialService {
	
	@Inject
	ProductRawMaterialRepository prmRepository;
	
	@Inject
	ProductRepository productRepository;
	
	@Inject
	RawMaterialRepository rmRepository;
	
	@Transactional
	public ProductRawMaterial associate (ProductRawMaterialDTO dto) {
		
		Product product = productRepository.findById(dto.productId);
		
		if (product == null) {
			throw new NotFoundException("Product not found");
		}
		
		RawMaterial rawMaterial = rmRepository.findById(dto.rawMaterial);
		
		if (rawMaterial == null) {
			throw new NotFoundException("Raw material not found");
	    }
		 
		if (dto.requiredQuantity == null || dto.requiredQuantity <= 0) {
			throw new WebApplicationException(
					 "Required quantity must be greater than zero", 400
					 );
		}
		
		ProductRawMaterial prm = new ProductRawMaterial();
		 
		prm.product = product;
		prm.rawMaterial = rawMaterial;
		prm.requireQuantity = dto.requiredQuantity;
		prmRepository.persist(prm);
		
		return prm;
		
		
	}
	

	public List<ProductRawMaterial> listByProduct(Long productId){
		return prmRepository.list("product.id",productId);
	}
	
	@Transactional
	public void delete (Long id) {
		boolean deleted = prmRepository.deleteById(id);

        if (!deleted) {
            throw new NotFoundException("Product raw material relation not found");
        }
	}
	
}

package com.autoflex.resource;

import java.util.List;

import com.autoflex.dto.ProductRawMaterialDTO;
import com.autoflex.entity.Product;
import com.autoflex.entity.ProductRawMaterial;
import com.autoflex.entity.RawMaterial;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/product-raw-material")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRawMaterialResource {
	
	@POST
	@Transactional
	public ProductRawMaterial associate (ProductRawMaterialDTO dto) {
		
		Product product = Product.findById(dto.productId);
		RawMaterial rawMaterial = RawMaterial.findById(dto.rawMaterial);
		
		if(product == null || rawMaterial == null) {
			throw new NotFoundException("Product or Raw Material not found");
		}
		
		ProductRawMaterial pm = new ProductRawMaterial();
		pm.product = product;
		pm.rawMaterial = rawMaterial;
		pm.requireQuantity = dto.requiredQuantity;
		pm.persist();
		
		return pm;
		
		
	}
	
	@GET
	@Path("/product/{productId}")
	public List<ProductRawMaterial> list(@PathParam("productId") Long productId){
		return ProductRawMaterial.list("product.id",productId);
	}
	
	@DELETE
	@Path("/product/{id}")
	@Transactional
	public void delete (@PathParam("id") Long id) {
		ProductRawMaterial.deleteById(id);
	}
}

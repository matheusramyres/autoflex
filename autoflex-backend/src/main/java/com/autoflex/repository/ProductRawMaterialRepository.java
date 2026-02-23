package com.autoflex.repository;

import com.autoflex.entity.ProductRawMaterial;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRawMaterialRepository implements PanacheRepository<ProductRawMaterial>{
	
	public long deleteByProductId(Long productId) {
	    return delete("product.id", productId);
	}

}

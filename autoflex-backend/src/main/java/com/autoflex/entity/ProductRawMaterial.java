package com.autoflex.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductRawMaterial extends PanacheEntity{
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	public Product product;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "raw_material_id")
	public RawMaterial rawMaterial;
	
	public Integer requireQuantity;
}

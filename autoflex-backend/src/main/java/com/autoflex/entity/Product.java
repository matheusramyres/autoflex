package com.autoflex.entity;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Product extends PanacheEntity{

	@Column(nullable = false)
	public String name;
	
	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal price;
}

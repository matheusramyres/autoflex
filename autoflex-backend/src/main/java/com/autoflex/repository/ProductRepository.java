package com.autoflex.repository;

import jakarta.enterprise.context.ApplicationScoped;

import com.autoflex.entity.Product;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}

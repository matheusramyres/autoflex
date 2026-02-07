package com.autoflex.resource;
import com.autoflex.entity.Product;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	@GET
	public List<Product> list(){
		return Product.listAll();
	}
	
	@GET
	@Path("/{id}")
	public Product findById(@PathParam("id") Long id) {
		return Product.findById(id);
	}
	
	@POST
	@Transactional
	public Product create (Product product) {
		product.persist();
		return product;
	}
	
	@PUT
	@Path("/{id}")
	@Transactional
	public Product update (@PathParam("id") Long  id, Product product) {
		Product entity = Product.findById(id);
		entity.name = product.name;
		entity.price = product.price;
		
		return entity;
		
	}
	
	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete (@PathParam("id") Long id) {
		Product.deleteById(id);
	}
}

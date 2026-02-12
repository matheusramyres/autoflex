package com.autoflex.resource;
import com.autoflex.entity.Product;
import com.autoflex.service.ProductionService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	@Inject
	ProductionService productionService;
	
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
    public void delete(@PathParam("id") Long id) {
        productionService.deleteProduct(id);
    }
}

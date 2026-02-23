package com.autoflex.resource;
import com.autoflex.dto.ProductWithMaterialsDTO;
import com.autoflex.entity.Product;
import com.autoflex.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	@Inject
	ProductService productService;
	
	@GET
	public List<Product> list(){
		return productService.list();
	}
	
	@GET
	@Path("/with-materials")
	public List<ProductWithMaterialsDTO> listWithMaterials(){
		return productService.listProductsWithMaterials();
	}
	
	@GET
	@Path("/{id}")
	public Product findById(@PathParam("id") Long id) {
		return productService.findById(id);
	}
	
	@POST
	public Product create (Product product) {
		return productService.create(product);
	}
	
	@PUT
	@Path("/{id}")
	public Product update (@PathParam("id") Long  id, Product product) {
		Product entity = productService.update(id, product);

		return entity;
		
	}
	
	@DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
		productService.deleteProduct(id);
        return Response.noContent().build();
    }
	
}

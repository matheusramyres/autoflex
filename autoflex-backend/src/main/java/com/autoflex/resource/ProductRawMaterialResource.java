package com.autoflex.resource;

import java.util.List;

import com.autoflex.dto.ProductRawMaterialDTO;
import com.autoflex.entity.ProductRawMaterial;
import com.autoflex.service.ProductRawMaterialService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/product-raw-material")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRawMaterialResource {
	
	@Inject
	ProductRawMaterialService prmService;	
	
	@POST
	public ProductRawMaterial associate (ProductRawMaterialDTO dto) {
		return prmService.associate(dto);
		
	}
	
	@GET
	@Path("product/{productId}")
	public List<ProductRawMaterial> list(@PathParam("productId") Long productId){
		return prmService.listByProduct(productId);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete (@PathParam("id") Long id) {
		prmService.delete(id);
        return Response.noContent().build();
	}
}

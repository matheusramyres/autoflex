package com.autoflex.resource;
import java.util.List;

import com.autoflex.entity.RawMaterial;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("raw-materials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RawMaterialResource {

	@GET
	public List<RawMaterial> list (){
		return RawMaterial.listAll();
	}
	
	@GET
	@Path("/{id}")
	public RawMaterial findById (@PathParam("id") Long id) {
		return RawMaterial.findById(id);
	}
	
	@POST
	@Transactional
	public RawMaterial create(RawMaterial material) {
		material.persist();
		return material;
	}
	
	@PUT
	@Path("/{id}")
	@Transactional
	public RawMaterial update (@PathParam("id") Long id, RawMaterial material) {
		RawMaterial entity = RawMaterial.findById(id);
		entity.name = material.name;
		entity.stockQuantity = material.stockQuantity;
		
		return entity;
	}
	
	@DELETE
	@Path("/{id}")
	@Transactional
	public Response delete(@PathParam("id") Long id) {
		try {
			RawMaterial.deleteById(id);			
		}catch(Exception ex) {
			throw new WebApplicationException(
		            "Unable to delete material",
		            409
		        );
		}
		return Response.noContent().build();
	}
	
}

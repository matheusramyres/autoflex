package com.autoflex.resource;

import com.autoflex.dto.RawMaterialDTO;
import com.autoflex.entity.RawMaterial;
import com.autoflex.service.RawMaterialService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("raw-materials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RawMaterialResource {
	
	@Inject
	RawMaterialService rawMaterialService;

	@GET
	public RawMaterialDTO list (){
		return rawMaterialService.list();
	}
	
	@GET
	@Path("/{id}")
	public RawMaterial findById (@PathParam("id") Long id) {
		return rawMaterialService.findById(id);
	}
	
	@POST
	public Response create(RawMaterial material) {
		RawMaterial created = rawMaterialService.create(material);
		return Response
				.status(Response.Status.CREATED)
				.entity(created)
				.build();
	}
	
	@PUT
	@Path("/{id}")
	public RawMaterial update (@PathParam("id") Long id, RawMaterial material) {
		return rawMaterialService.update(id, material);
		
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		rawMaterialService.delete(id);			
		return Response.noContent().build();
	}
	
}

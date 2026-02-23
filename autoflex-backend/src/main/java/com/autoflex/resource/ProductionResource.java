package com.autoflex.resource;

import com.autoflex.service.ProductionService;

import java.util.List;
import com.autoflex.dto.ProductionSuggestionDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/production")
@Produces(MediaType.APPLICATION_JSON)
public class ProductionResource {
	
	@Inject
	ProductionService productionService;
	
	@GET
	@Path("/suggestions")
	public List<ProductionSuggestionDTO> getSuggestion(){
		return productionService.calculateProduction();
	}

	
}

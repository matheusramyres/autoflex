package com.autoflex.resource;

import com.autoflex.dto.DashboardSummaryDTO;
import com.autoflex.dto.VariationDTO;
import com.autoflex.repository.ProductRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {
	
	@Inject
    ProductRepository productRepository;

    @GET
    @Path("/summary")
    public DashboardSummaryDTO getSummary() {
    	long totalProducts = productRepository.count();
        return new DashboardSummaryDTO(
            249135.00,
            (int) totalProducts,
            87.3,
            new VariationDTO(12.5, 8, -2.1)
        );
    }
}

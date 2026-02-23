package com.autoflex.resource;

import com.autoflex.dto.DashboardSummaryDTO;
import com.autoflex.dto.VariationDTO;
import com.autoflex.repository.ProductRepository;
import com.autoflex.service.DashboardService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {
	
	@Inject
    DashboardService dashboarService;

    @GET
    @Path("/summary")
    public DashboardSummaryDTO getSummary() {
        return dashboarService.getSummary();
    }
}

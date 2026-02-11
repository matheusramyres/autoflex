package com.autoflex.resource;

import com.autoflex.dto.DashboardSummaryDTO;
import com.autoflex.dto.VariationDTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    @GET
    @Path("/summary")
    public DashboardSummaryDTO getSummary() {
        return new DashboardSummaryDTO(
            249135.00,
            124,
            87.3,
            new VariationDTO(12.5, 8, -2.1)
        );
    }
}

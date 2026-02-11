package com.autoflex.dto;

public record DashboardSummaryDTO (

		    Double totalProductionValue,
		    Integer activeProducts,
		    Double materialUtilization,
		    VariationDTO variation
		) {}


package com.autoflex.service;

import com.autoflex.dto.DashboardSummaryDTO;
import com.autoflex.dto.VariationDTO;
import com.autoflex.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DashboardService {
	@Inject
    ProductRepository productRepository;
	
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

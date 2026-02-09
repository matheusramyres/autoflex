package com.autoflex.service;


import com.autoflex.dto.ProductionSuggestionDTO;
import com.autoflex.entity.Product;
import com.autoflex.entity.ProductRawMaterial;
import com.autoflex.entity.RawMaterial;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductionService {

	public List<ProductionSuggestionDTO> calculateProduction(){
		
		Map<Long, Integer> stockMap = RawMaterial.<RawMaterial>listAll()
				.stream()
				.collect(Collectors.toMap(
						 rm -> rm.id,
						 rm -> rm.stockQuantity
						));

		
		List<Product> products = Product.listAll(Sort.by("price").descending());
		
		List<ProductionSuggestionDTO> result = new ArrayList<>();
		
		for(Product product: products) {
			
			List<ProductRawMaterial> materials = ProductRawMaterial.list("product.id", product.id);
			
			if(materials.isEmpty()) continue;
			
			int maxQuantity = Integer.MAX_VALUE;
			
			for(ProductRawMaterial prm: materials) {
				Integer availableStock = stockMap.get(prm.product.id);
				int possible = availableStock / prm.requireQuantity;
				maxQuantity = Math.min(maxQuantity, possible);
			}
			
			if(maxQuantity > 0) {
				for(ProductRawMaterial prm: materials) {
					Long rmId = prm.rawMaterial.id;
					int updateStock = stockMap.get(rmId) - (maxQuantity * prm.requireQuantity);
					stockMap.put(rmId, updateStock);
					
				}
				
				ProductionSuggestionDTO dto = new ProductionSuggestionDTO();
				dto.productId = product.id;
				dto.productName = product.name;
				dto.quantity = maxQuantity;
				dto.unitPrice = product.price;
				dto.totalValue = product.price.multiply(BigDecimal.valueOf(maxQuantity));
				
				result.add(dto);
			}
		}
		
		return result;

	}
}

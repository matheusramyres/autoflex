package com.autoflex.service;

import java.util.List;

import com.autoflex.dto.RawMaterialDTO;
import com.autoflex.entity.ProductRawMaterial;
import com.autoflex.entity.RawMaterial;
import com.autoflex.repository.RawMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class RawMaterialService {
	
	@Inject
	RawMaterialRepository rawMaterialRepository;
	
	public RawMaterialDTO list (){
		
		List<RawMaterial> materials = rawMaterialRepository.listAll();
		int totalStock = materials.stream()
				.mapToInt(material-> material.stockQuantity)
				.sum();
		int totalMaterials = materials.size();
		int stockLow = (int) materials.stream()
				.filter(material -> material.stockQuantity <= 30)
				.count();
		
		RawMaterialDTO dto = new RawMaterialDTO();
		dto.totalStock = totalStock;
		dto.totalMaterials = totalMaterials;
		dto.stockLow = stockLow;
		dto.items = materials;

		
		return dto;
	}
	
	public RawMaterial findById (Long id) {
		return rawMaterialRepository.findByIdOptional(id)
				.orElseThrow(()->new WebApplicationException("Raw material not found", 404));
	}
	
	
	@Transactional
	public RawMaterial create(RawMaterial material) {
		rawMaterialRepository.persist(material);
		return material;
	}
	
	
	@Transactional
	public RawMaterial update (Long id, RawMaterial material) {
		RawMaterial entity = rawMaterialRepository.findByIdOptional(id)
			    .orElseThrow(() -> new NotFoundException("Raw material not found"));

		entity.name = material.name;
		entity.stockQuantity = material.stockQuantity;
		
		return entity;
	}
	
	@Transactional
	public void delete(Long id) {

	    RawMaterial material = rawMaterialRepository.findByIdOptional(id)
	        .orElseThrow(() -> new NotFoundException("Raw material not found"));

	    boolean inUse = ProductRawMaterial.count("rawMaterial.id", id) > 0;

	    if (inUse) {
	        throw new WebApplicationException(
	            "Raw material is in use by products",
	            409
	        );
	    }

	    rawMaterialRepository.delete(material);
	}

	
	
}

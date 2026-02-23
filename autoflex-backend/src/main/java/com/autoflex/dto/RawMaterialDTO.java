package com.autoflex.dto;

import java.util.List;

import com.autoflex.entity.RawMaterial;

public class RawMaterialDTO {
	public int totalStock;
	public int totalMaterials;
	public int stockLow;
	public List<RawMaterial> items;
}

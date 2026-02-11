package com.autoflex.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductWithMaterialsDTO {
    public Long id;
    public String name;
    public BigDecimal price;
    public Integer rawMaterialQuantity;
    public List<ProductMaterialDTO> materials;
}
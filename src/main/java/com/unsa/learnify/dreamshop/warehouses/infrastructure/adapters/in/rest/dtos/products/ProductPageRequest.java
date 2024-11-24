package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPageSize;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPositiveInteger;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPageRequest {
    @NotBlank(message = "Page is required and cannot be blank.")
    @ValidPositiveInteger
    private String page = "0";
    @NotBlank(message = "Size is required and cannot be blank.")
    @ValidPageSize
    private String size = "10";
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;
    @DecimalMin(value = "0.01", message = "Minimum price must be at least 0.01.")
    private BigDecimal minPrice;
    @DecimalMax(value = "1000000.00", message = "Maximum price must be at most 1,000,000.00.")
    private BigDecimal maxPrice;
    @ValidPositiveInteger
    private String minQuantity;
    @ValidPositiveInteger
    private String maxQuantity;
    @ValidPositiveInteger
    private String categoryId;
}

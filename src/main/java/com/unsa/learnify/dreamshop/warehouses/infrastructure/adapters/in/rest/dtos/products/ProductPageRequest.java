package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPageSize;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPositiveInteger;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Query parameters for paginating and filtering the list of products.")
public class ProductPageRequest {
    @Schema(
        description = "Page number for pagination. Must be a positive integer.",
        example = "0"
    )
    @NotBlank(message = "Page is required and cannot be blank.")
    @ValidPositiveInteger
    private String page = "0";
    @Schema(
        description = "Page size for pagination. Must be a positive integer and within the allowed range.",
        example = "10"
    )
    @NotBlank(message = "Size is required and cannot be blank.")
    @ValidPageSize
    private String size = "10";
    @Schema(
        description = "Name filter to search for products. Maximum 100 characters.",
        example = "Smartphone"
    )
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;
    @Schema(
        description = "Minimum price for filtering products. Must be at least 0.01.",
        example = "100.00"
    )
    @DecimalMin(value = "0.01", message = "Minimum price must be at least 0.01.")
    private BigDecimal minPrice;
    @Schema(
        description = "Maximum price for filtering products. Must be at most 1,000,000.00.",
        example = "1000.00"
    )
    @DecimalMax(value = "1000000.00", message = "Maximum price must be at most 1,000,000.00.")
    private BigDecimal maxPrice;
    @Schema(
        description = "Minimum quantity of products in stock. Must be a positive integer.",
        example = "5"
    )
    @ValidPositiveInteger
    private String minQuantity;
    @Schema(
        description = "Maximum quantity of products in stock. Must be a positive integer.",
        example = "100"
    )
    @ValidPositiveInteger
    private String maxQuantity;
    @Schema(
        description = "Category id for filtering products. Must be a positive integer.",
        example = "1"
    )
    @ValidPositiveInteger
    private String categoryId;
}

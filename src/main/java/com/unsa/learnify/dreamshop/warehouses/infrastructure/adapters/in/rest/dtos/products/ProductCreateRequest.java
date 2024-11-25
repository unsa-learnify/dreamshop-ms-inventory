package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidCurrency;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request object for creating a new product.")
public record ProductCreateRequest(
    @Schema(
        description = "Name of the product. Must be between 1 and 255 characters.",
        example = "Smartphone"
    )
    @NotBlank
    @Size(min = 1, max = 255)
    String name,
    @Schema(
        description = "Description of the product.",
        example = "A high-end smartphone with 128GB storage and a 48MP camera."
    )
    @NotBlank
    String description,
    @Schema(
        description = "Price of the product. Must be a positive value greater than zero.",
        example = "799.99"
    )
    @NotNull
    @DecimalMin(value = "0.01", message = "The price must be greater than zero.")
    BigDecimal unitPrice,
    @Schema(
        description = "Currency of the product's price. Must be a valid ISO 4217 currency code.",
        example = "USD"
    )
    @NotBlank
    @ValidCurrency
    String currency
) { }

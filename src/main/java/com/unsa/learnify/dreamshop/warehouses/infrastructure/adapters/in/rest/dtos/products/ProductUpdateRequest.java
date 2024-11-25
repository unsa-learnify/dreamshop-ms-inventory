package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidCurrency;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Fields for updating a product. Only non-null fields will be updated.")
public record ProductUpdateRequest(
    @Schema(
        description = "New name of the product. Must be between 1 and 255 characters.",
        example = "Updated Electric Bike"
    )
    @Size(min = 1, max = 255)
    String name,
    @Schema(
        description = "New description of the product.",
        example = "An updated eco-friendly electric bike with a longer range."
    )
    String description,
    @Schema(
        description = "New unit price of the product. Must be greater than zero.",
        example = "900.00"
    )
    @DecimalMin(value = "0.01", message = "The price must be greater than zero.")
    BigDecimal unitPrice,
    @Schema(
        description = "New currency of the product's price. Must be a valid currency code.",
        example = "EUR"
    )
    @ValidCurrency
    String currency
) {}

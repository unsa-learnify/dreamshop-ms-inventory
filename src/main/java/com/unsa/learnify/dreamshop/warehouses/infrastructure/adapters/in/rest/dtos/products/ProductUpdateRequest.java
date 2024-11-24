package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidCurrency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateRequest(
    @Size(min = 1, max = 255)
    String name,
    String description,
    @DecimalMin(value = "0.01", message = "The price must be greater than zero.")
    BigDecimal unitPrice,
    @ValidCurrency
    String currency
) { }

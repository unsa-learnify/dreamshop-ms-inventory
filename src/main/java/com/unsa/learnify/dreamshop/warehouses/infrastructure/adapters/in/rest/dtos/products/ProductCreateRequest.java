package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidCurrency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequest(
    @NotBlank
    @Size(min = 1, max = 255)
    String name,
    @NotBlank
    String description,
    @NotNull
    @DecimalMin(value = "0.01", message = "The price must be greater than zero.")
    BigDecimal unitPrice,
    @NotBlank
    @ValidCurrency
    String currency
) { }

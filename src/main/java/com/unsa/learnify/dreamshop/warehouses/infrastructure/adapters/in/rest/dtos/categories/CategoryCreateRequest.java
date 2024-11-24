package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateRequest(
    @NotBlank
    @Size(min = 1, max = 255)
    String name,
    @NotBlank
    String description
) { }

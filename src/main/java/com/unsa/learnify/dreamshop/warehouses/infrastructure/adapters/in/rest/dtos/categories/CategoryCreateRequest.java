package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request body for creating a new category.")
public record CategoryCreateRequest(
    @Schema(
        description = "Name of the category.",
        example = "Electronics"
    )
    @NotBlank
    @Size(min = 1, max = 255)
    String name,
    @Schema(
        description = "Description of the category.",
        example = "Devices and gadgets for everyday use."
    )
    @NotBlank
    String description
) {}

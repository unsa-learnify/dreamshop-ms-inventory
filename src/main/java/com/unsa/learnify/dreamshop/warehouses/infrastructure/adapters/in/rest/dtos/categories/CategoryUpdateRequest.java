package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Size;

@Schema(description = "Request object for updating category details.")
public record CategoryUpdateRequest(
    @Schema(
        description = "New name of the category. Must be between 1 and 255 characters.",
        example = "Updated Electronics"
    )
    @Size(min = 1, max = 255)
    String name,
    @Schema(
        description = "New description of the category.",
        example = "Updated description for the electronics category."
    )
    String description
) { }

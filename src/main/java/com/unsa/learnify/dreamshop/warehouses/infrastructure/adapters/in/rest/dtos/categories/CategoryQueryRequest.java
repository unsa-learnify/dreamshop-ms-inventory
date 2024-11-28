package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPageSize;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPositiveInteger;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Query parameters for filtering and paginating categories.")
public class CategoryQueryRequest {
    @Schema(
        description = "Page number for pagination. Must be a positive integer.",
        example = "0",
        defaultValue = "0"
    )
    @NotBlank
    @ValidPositiveInteger
    private String page = "0";
    @Schema(
        description = "Number of items per page. Must be between 1 and 100.",
        example = "10",
        defaultValue = "10"
    )
    @NotBlank
    @ValidPageSize
    private String size = "10";
    @Schema(
        description = "Filter categories by name. Allows up to 100 characters with letters, numbers, and spaces only.",
        example = "Electronics"
    )
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9\\s]*$",
        message = "Name must contain only letters, numbers, and spaces"
    )
    private String name;
    @Schema(
        description = "Filter categories by description. Allows up to 100 characters with letters, numbers, and spaces only.",
        example = "Gadgets and electronic devices"
    )
    @Size(max = 100, message = "Description must be at most 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9\\s]*$",
        message = "Name must contain only letters, numbers, and spaces"
    )
    private String description;
}

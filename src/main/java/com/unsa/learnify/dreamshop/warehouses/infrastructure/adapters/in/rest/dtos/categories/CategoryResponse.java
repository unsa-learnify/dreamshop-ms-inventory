package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object representing a category.")
public class CategoryResponse {
    @Schema(
        description = "Unique identifier of the category.",
        example = "1"
    )
    private Integer id;
    @Schema(
        description = "Name of the category.",
        example = "Electronics"
    )
    private String name;
    @Schema(
        description = "Description of the category.",
        example = "Devices and gadgets for everyday use."
    )
    private String description;
    @Schema(
        description = "Timestamp when the category was created.",
        example = "2024-11-24T12:00:00"
    )
    private LocalDateTime createdAt;
    @Schema(
        description = "Timestamp when the category was last updated.",
        example = "2024-11-25T08:30:00"
    )
    private LocalDateTime updatedAt;
}

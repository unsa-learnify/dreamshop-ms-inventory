package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Details of a product, including its attributes and timestamps.")
public class ProductResponse {
    @Schema(
        description = "Unique identifier of the product.",
        example = "1"
    )
    private Integer id;
    @Schema(
        description = "Name of the product.",
        example = "Electric Bike"
    )
    private String name;
    @Schema(
        description = "Description of the product.",
        example = "An eco-friendly electric bike with a 60-mile range and pedal assist."
    )
    private String description;
    @Schema(
        description = "Unique code of the product, which must follow the pattern xxx-xx-xxx-xxxx-x.",
        example = "123-45-678-9012-3"
    )
    private String code;
    @Schema(
        description = "Unit price of the product.",
        example = "850.00"
    )
    private BigDecimal unitPrice;
    @Schema(
        description = "Currency of the product's price.",
        example = "USD"
    )
    private String currency;
    @Schema(
        description = "Quantity of the product available in stock.",
        example = "10"
    )
    private Integer quantity;
    private Set<CategoryResponse> categories;
    @Schema(
        description = "Timestamp when the product was created.",
        example = "2024-01-01T12:00:00"
    )
    private LocalDateTime createdAt;
    @Schema(
        description = "Timestamp when the product was last updated.",
        example = "2024-01-15T15:30:00"
    )
    private LocalDateTime updatedAt;
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response structure for paginated results.")
public class PaginationResultResponse<T> {
    @Schema(
        description = "Total number of items available across all pages.",
        example = "150",
        defaultValue = "0"
    )
    private Long totalItems;
    @Schema(
        description = "Total number of pages available based on the page size.",
        example = "15",
        defaultValue = "0"
    )
    private Integer totalPages;
    @Schema(
        description = "Current page number being displayed.",
        example = "1",
        defaultValue = "0"
    )
    private Integer currentPage;
    @Schema(
        description = "Number of items per page.",
        example = "10",
        defaultValue = "10"
    )
    private Integer pageSize;
    @Schema(
        description = "Indicates whether there are more items available on the next page.",
        example = "true",
        defaultValue = "false"
    )
    private Boolean hasNextPage;
    @Schema(
        description = "List of items for the current page. Can be any type depending on the context.",
        example = "[{\"id\": 1, \"name\": \"Electronics\"}, {\"id\": 2, \"name\": \"Furniture\"}]",
        defaultValue = "[]"
    )
    private List<T> items;
}

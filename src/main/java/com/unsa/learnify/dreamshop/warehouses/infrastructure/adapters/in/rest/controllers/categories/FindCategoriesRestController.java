package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoriesServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Page;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryQueryRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.PaginationResultResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.IntegerUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class FindCategoriesRestController {
    private final FindCategoriesServicePort findCategoriesServicePort;
    public FindCategoriesRestController(FindCategoriesServicePort findCategoriesServicePort) {
        this.findCategoriesServicePort = findCategoriesServicePort;
    }
    @Operation(
        summary = "Retrieve a list of categories with optional filters",
        description = "Fetches a paginated list of categories based on the provided filters.",
        tags = {"Categories"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "List of categories retrieved successfully.",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema =  @Schema(implementation = CategoryResponse.class)))
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No categories found matching the provided filters."
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid query parameters.",
            content = @Content(mediaType = "application/json")
        )
    })
    @GetMapping
    public ResponseEntity<PaginationResultResponse<CategoryResponse>> findCategoriesByPage(
        @ModelAttribute @Valid CategoryQueryRequest categoryQueryRequest
    ) {
        Integer page = IntegerUtils.safeParseInteger(categoryQueryRequest.getPage(), 0);
        Integer size = IntegerUtils.safeParseInteger(categoryQueryRequest.getSize(), 10);
        Page pageable = Page.builder().number(page).size(size).build();
        CategoryFilters categoryFilters = CategoryFilters.builder()
            .page(pageable)
            .name(categoryQueryRequest.getName())
            .description(categoryQueryRequest.getDescription())
            .build();
        PaginationResult<Category> categoryPage = this.findCategoriesServicePort.execute(categoryFilters);
        if (categoryPage.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CategoryRestMapper.domainToPaginationResponse(categoryPage));
    }
}

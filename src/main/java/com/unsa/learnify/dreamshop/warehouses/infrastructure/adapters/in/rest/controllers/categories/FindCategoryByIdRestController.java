package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.services.categories.FindCategoryByIdService;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class FindCategoryByIdRestController {
    private final FindCategoryByIdService findCategoryByIdServicePort;
    public FindCategoryByIdRestController(FindCategoryByIdService findCategoryByIdServicePort) {
        this.findCategoryByIdServicePort = findCategoryByIdServicePort;
    }
    @Operation(
        summary = "Retrieve a category by id",
        description = "Fetches the details of a single category using its unique id.",
        tags = {"Categories"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Category retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Category not found. No category exists with the provided id."
        )
    })
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findCategoryById(
        @Parameter(
            description = "Id of the category to retrieve.",
            example = "1",
            required = true
        )
        @PathVariable Integer categoryId
    ) throws CategoryNotFoundException {
        Category category = this.findCategoryByIdServicePort.execute(categoryId);
        return ResponseEntity.ok(CategoryRestMapper.domainToResponse(category));
    }
}

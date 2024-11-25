package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoryByNameServicePort;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class FindCategoryByNameRestController {
    private final FindCategoryByNameServicePort findCategoryByNameServicePort;
    public FindCategoryByNameRestController(FindCategoryByNameServicePort findCategoryByNameServicePort) {
        this.findCategoryByNameServicePort = findCategoryByNameServicePort;
    }
    @Operation(
        summary = "Retrieve a category by name",
        description = "Fetches the details of a single category based on the provided name.",
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
            description = "Category not found. No category exists with the provided name."
        )
    })
    @GetMapping("/name")
    public ResponseEntity<CategoryResponse> findCategoryByName(
        @Parameter(
            description = "The name of the category to search for.",
            example = "Electronics",
            required = true
        )
        @RequestParam String query
    ) throws CategoryNotFoundException {
        Category category = this.findCategoryByNameServicePort.execute(query);
        return ResponseEntity.ok(CategoryRestMapper.domainToResponse(category));
    }
}

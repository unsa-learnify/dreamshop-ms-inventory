package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.UpdateOneCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryUpdateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class UpdateOneCategoryByIdRestController {
    private final UpdateOneCategoryByIdServicePort updateOneCategoryByIdServicePort;
    public UpdateOneCategoryByIdRestController(UpdateOneCategoryByIdServicePort updateOneCategoryByIdServicePort) {
        this.updateOneCategoryByIdServicePort = updateOneCategoryByIdServicePort;
    }
    @Operation(
        summary = "Update a category by id",
        description = "Partially updates the details of a category identified by its unique id. Fields not provided in the request will remain unchanged.",
        tags = {"Categories"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Category updated successfully. No content is returned."
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Category not found. No category exists with the provided id."
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Category name already exists. The name must be unique across all categories."
        )
    })
    @PatchMapping("/{categoryId}")
    public ResponseEntity<Void> updateOneCategoryById(
        @Parameter(
            description = "Id of the category to update.",
            example = "1",
            required = true
        )
        @PathVariable Integer categoryId,
        @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest
    ) throws CategoryDuplicatedException, CategoryNotFoundException {
        Category category = CategoryRestMapper.updateRequestToDomain(categoryId, categoryUpdateRequest);
        this.updateOneCategoryByIdServicePort.execute(categoryId, category);
        return ResponseEntity.noContent().build();
    }
}

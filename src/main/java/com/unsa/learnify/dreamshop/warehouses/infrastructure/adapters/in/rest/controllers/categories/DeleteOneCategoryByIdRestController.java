package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.DeleteOneCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class DeleteOneCategoryByIdRestController {
    private final DeleteOneCategoryByIdServicePort deleteOneCategoryByIdServicePort;
    public DeleteOneCategoryByIdRestController(DeleteOneCategoryByIdServicePort deleteOneCategoryByIdServicePort) {
        this.deleteOneCategoryByIdServicePort = deleteOneCategoryByIdServicePort;
    }
    @Operation(
        summary = "Delete a category by id",
        description = "Deletes a category from the system using its unique id.",
        tags = {"Categories"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Category deleted successfully."
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Category not found. No category exists with the provided id."
        )
    })
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteOneCategoryById(
        @Parameter(
            description = "Id of the category to delete.",
            example = "1",
            required = true
        )
        @PathVariable Integer categoryId
    ) throws CategoryNotFoundException {
        this.deleteOneCategoryByIdServicePort.execute(categoryId);
        return ResponseEntity.noContent().build();
    }
}

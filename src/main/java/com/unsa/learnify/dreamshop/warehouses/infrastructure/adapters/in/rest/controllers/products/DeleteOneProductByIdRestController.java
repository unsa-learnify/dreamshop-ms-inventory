package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.DeleteOneProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;

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
@RequestMapping("/api/v1/products")
public class DeleteOneProductByIdRestController {
    private final DeleteOneProductByIdServicePort deleteOneProductByIdServicePort;
    public DeleteOneProductByIdRestController(DeleteOneProductByIdServicePort deleteOneProductByIdServicePort) {
        this.deleteOneProductByIdServicePort = deleteOneProductByIdServicePort;
    }
    @Operation(
        summary = "Delete a product by id",
        description = "Deletes a product from the database using its unique identifier.",
        tags = {"Products"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Product deleted successfully."
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Product not found. No product exists with the provided id."
        )
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteOneProductById(
        @Parameter(
            description = "Id of the product to delete.",
            example = "1",
            required = true
        )
        @PathVariable Integer productId
    ) throws ProductNotFoundException {
        this.deleteOneProductByIdServicePort.execute(productId);
        return ResponseEntity.noContent().build();
    }
}

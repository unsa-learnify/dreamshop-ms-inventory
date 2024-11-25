package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.UpdateOneProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CurrencyNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductUpdateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class UpdateOneProductByIdRestController {
    private final UpdateOneProductByIdServicePort updateProductByIdService;
    public UpdateOneProductByIdRestController(UpdateOneProductByIdServicePort updateProductByIdService) {
        this.updateProductByIdService = updateProductByIdService;
    }
    @Operation(
        summary = "Update a product by id",
        description = "Updates the details of a product using its unique id. Only the fields provided in the request body will be updated.",
        tags = {"Products"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Product updated successfully."
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Product not found. No product exists with the provided id."
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Conflict. A product with the same name already exists."
        )
    })
    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProductById(
        @Parameter(
            description = "Id of the product to update.",
            example = "1",
            required = true
        )
        @PathVariable Integer productId,
        @RequestBody @Valid ProductUpdateRequest productUpdateRequest
    ) throws ProductDuplicatedException, ProductNotFoundException, CurrencyNotFoundException {
        Product product = ProductRestMapper.updateRequestToDomain(productId, productUpdateRequest);
        this.updateProductByIdService.execute(productId, product);
        return ResponseEntity.noContent().build();
    }
}

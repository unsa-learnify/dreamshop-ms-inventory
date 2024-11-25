package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

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
@RequestMapping("/api/v1/products")
public class FindProductByIdRestController {
    private final FindProductByIdServicePort findProductByIdServicePort;
    public FindProductByIdRestController(FindProductByIdServicePort findProductByIdServicePort) {
        this.findProductByIdServicePort = findProductByIdServicePort;
    }
    @Operation(
        summary = "Retrieve a product by id",
        description = "Fetches the details of a single product using its unique identifier.",
        tags = {"Products"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Product retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Product not found. No product exists with the provided id."
        )
    })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById(
        @Parameter(
            description = "Id of the product to retrieve.",
            example = "1",
            required = true
        )
        @PathVariable Integer productId
    ) throws ProductNotFoundException {
        Product product = this.findProductByIdServicePort.execute(productId);
        return ResponseEntity.ok(ProductRestMapper.domainToResponse(product));
    }
}

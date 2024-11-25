package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductByNameServicePort;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class FindProductByNameRestController {
    private final FindProductByNameServicePort findProductByNameServicePort;
    public FindProductByNameRestController(FindProductByNameServicePort findProductByNameServicePort) {
        this.findProductByNameServicePort = findProductByNameServicePort;
    }
    @Operation(
        summary = "Retrieve a product by name",
        description = "Fetches the details of a product using its name. The name is case-insensitive.",
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
            description = "Product not found. No product exists with the provided name."
        )
    })
    @GetMapping("/name")
    public ResponseEntity<ProductResponse> findProductByName(
        @Parameter(
            description = "Name of the product to search.",
            example = "Electric Bike",
            required = true
        )
        @RequestParam String query
    ) throws ProductNotFoundException {
        Product product = this.findProductByNameServicePort.execute(query);
        return ResponseEntity.ok(ProductRestMapper.domainToResponse(product));
    }
}

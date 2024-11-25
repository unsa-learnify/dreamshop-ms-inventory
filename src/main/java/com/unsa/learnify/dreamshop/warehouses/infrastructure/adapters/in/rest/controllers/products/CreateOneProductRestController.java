package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.CreateOneProductServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductCreateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
public class CreateOneProductRestController {
    private final CreateOneProductServicePort createOneProductServicePort;
    public CreateOneProductRestController(CreateOneProductServicePort createOneProductServicePort) {
        this.createOneProductServicePort = createOneProductServicePort;
    }
    @Operation(
        summary = "Create a new product",
        description = "Adds a new product to the catalog with the provided details.",
        tags = {"Products"}
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Product created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Product already exists. A product with the same name already exists in the catalog."
        )
    })
    @PostMapping
    public ResponseEntity<ProductResponse> createOneCategory(@RequestBody @Valid ProductCreateRequest productCreateRequest) throws ProductDuplicatedException {
        Product product = ProductRestMapper.createRequestToDomain(productCreateRequest);
        Product savedCategory = this.createOneProductServicePort.execute(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(ProductRestMapper.domainToResponse(savedCategory));
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.AddCategoriesToProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductAddCategoriesRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
public class AddCategoriesToProductByIdRestController {
    private final AddCategoriesToProductByIdServicePort addCategoriesToProductByIdServicePort;
    public AddCategoriesToProductByIdRestController(
        AddCategoriesToProductByIdServicePort addCategoriesToProductByIdServicePort
    ) {
        this.addCategoriesToProductByIdServicePort = addCategoriesToProductByIdServicePort;
    }
    @PostMapping("/{productId}/categories")
    public ResponseEntity<ProductResponse> addCategoriesToProduct(
        @PathVariable Integer productId,
        @RequestBody @Valid ProductAddCategoriesRequest productAddCategoriesRequest
    ) throws CategoryNotFoundException, ProductNotFoundException {
        Product savedProduct = this.addCategoriesToProductByIdServicePort.execute(productId, productAddCategoriesRequest.getCategoryIds());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}").buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(ProductRestMapper.domainToResponse(savedProduct));
    }
}

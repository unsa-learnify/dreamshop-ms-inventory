package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.DeleteCategoriesToProductByIdServicePort;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductDeleteCategoriesRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class DeleteCategoriesToProductByIdRestController {
    private final DeleteCategoriesToProductByIdServicePort deleteCategoriesToProductByIdService;
    public DeleteCategoriesToProductByIdRestController(
        DeleteCategoriesToProductByIdServicePort deleteCategoriesToProductByIdService
    ) {
        this.deleteCategoriesToProductByIdService = deleteCategoriesToProductByIdService;
    }
    @DeleteMapping("/{productId}/categories")
    public ResponseEntity<Void> deleteCategoriesToProductById(
        @PathVariable Integer productId,
        @RequestBody @Valid ProductDeleteCategoriesRequest productDeleteCategoriesRequest
    ) throws ProductNotFoundException {
        this.deleteCategoriesToProductByIdService.execute(productId, productDeleteCategoriesRequest.getCategoryIds());
        return ResponseEntity.noContent().build();
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.UpdateOneProductByIdServicePort;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductUpdateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class UpdateOneProductByIdRestController {
    private final UpdateOneProductByIdServicePort updateProductByIdService;
    public UpdateOneProductByIdRestController(UpdateOneProductByIdServicePort updateProductByIdService) {
        this.updateProductByIdService = updateProductByIdService;
    }
    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProductById(@PathVariable Integer productId, @RequestBody ProductUpdateRequest productUpdateRequest) throws ProductDuplicatedException, ProductNotFoundException {
        Product product = ProductRestMapper.updateRequestToDomain(productId, productUpdateRequest);
        this.updateProductByIdService.execute(productId, product);
        return ResponseEntity.noContent().build();
    }
}

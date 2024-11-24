package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class FindProductByIdRestController {
    private FindProductByIdServicePort findProductByIdServicePort;
    public FindProductByIdRestController(FindProductByIdServicePort findProductByIdServicePort) {
        this.findProductByIdServicePort = findProductByIdServicePort;
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Integer productId) throws ProductNotFoundException {
        Product product = this.findProductByIdServicePort.execute(productId);
        return ResponseEntity.ok(ProductRestMapper.domainToResponse(product));
    }
}

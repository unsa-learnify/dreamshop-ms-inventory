package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductByNameServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;

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
    @GetMapping("/name")
    public ResponseEntity<ProductResponse> findProductByName(@RequestParam String query) throws ProductNotFoundException {
        Product product = this.findProductByNameServicePort.execute(query);
        return ResponseEntity.ok(ProductRestMapper.domainToResponse(product));
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.DeleteOneProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;

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
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteOneProductById(@PathVariable Integer productId) throws ProductNotFoundException {
        this.deleteOneProductByIdServicePort.execute(productId);
        return ResponseEntity.noContent().build();
    }
}

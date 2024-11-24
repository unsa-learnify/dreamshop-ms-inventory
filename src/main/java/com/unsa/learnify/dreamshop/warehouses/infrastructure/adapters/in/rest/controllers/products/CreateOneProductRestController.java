package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.CreateOneProductServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductCreateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;
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
    @PostMapping
    public ResponseEntity<ProductResponse> createOneCategory(@RequestBody @Valid ProductCreateRequest productCreateRequest) throws ProductDuplicatedException {
        Product product = ProductRestMapper.createRequestToDomain(productCreateRequest);
        Product savedCategory = this.createOneProductServicePort.execute(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(ProductRestMapper.domainToResponse(savedCategory));
    }
}

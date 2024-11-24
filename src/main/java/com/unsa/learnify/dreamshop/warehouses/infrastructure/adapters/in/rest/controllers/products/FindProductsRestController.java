package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductsServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Page;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductPageRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.ProductRestMapper;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.IntegerUtils;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class FindProductsRestController {
    private final FindProductsServicePort findProductsServicePort;
    public FindProductsRestController(FindProductsServicePort findProductsServicePort) {
        this.findProductsServicePort = findProductsServicePort;
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findProducts(@ModelAttribute @Valid ProductPageRequest productPageRequest) {
        Integer page = IntegerUtils.safeParseInteger(productPageRequest.getPage(), 0);
        Integer size = IntegerUtils.safeParseInteger(productPageRequest.getSize(), 0);
        Page pageable = Page.builder().number(page).size(size).build();
        ProductFilters productFilters = ProductFilters.builder()
            .page(pageable)
            .name(productPageRequest.getName())
            .minPrice(productPageRequest.getMinPrice())
            .maxPrice(productPageRequest.getMaxPrice())
            .minQuantity(IntegerUtils.safeParseInteger(productPageRequest.getMinQuantity(), null))
            .maxQuantity(IntegerUtils.safeParseInteger(productPageRequest.getMaxQuantity(), null))
            .categoryId(IntegerUtils.safeParseInteger(productPageRequest.getCategoryId(), null))
            .build();
        List<Product> products = this.findProductsServicePort.execute(productFilters);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products.stream().map(ProductRestMapper::domainToResponse).toList());
    }
}

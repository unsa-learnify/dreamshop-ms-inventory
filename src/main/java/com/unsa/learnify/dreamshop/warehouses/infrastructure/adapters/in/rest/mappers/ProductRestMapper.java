package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Currency;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductCreateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductUpdateRequest;

public class ProductRestMapper {
    private ProductRestMapper() {}
    public static ProductResponse domainToResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .unitPrice(product.getUnitPrice())
            .currency(product.getCurrency().getCode())
            .quantity(product.getQuantity())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }
    public static Product createRequestToDomain(ProductCreateRequest productCreateRequest) {
        return Product.builder()
            .id(null)
            .name(productCreateRequest.name())
            .description(productCreateRequest.description())
            .unitPrice(productCreateRequest.unitPrice())
            .currency(parseCurrency(productCreateRequest.currency()))
            .build();
    }
    public static Product updateRequestToDomain(Integer productId, ProductUpdateRequest productUpdateRequest) {
        return Product.builder()
            .id(productId)
            .name(productUpdateRequest.name())
            .description(productUpdateRequest.description())
            .unitPrice(productUpdateRequest.unitPrice())
            .currency(parseCurrency(productUpdateRequest.currency()))
            .build();
    }
    private static Currency parseCurrency(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return null;
        }
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equalsIgnoreCase(currencyCode)) {
                return currency;
            }
        }
        return Currency.DOLLAR;
    }
}

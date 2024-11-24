package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;

public class ProductPersistenceMapper {
    private ProductPersistenceMapper() {}
    public static ProductEntity domainToEntity(Product product) {
        return ProductEntity.builder()
            .id(null)
            .name(product.getName())
            .description(product.getDescription())
            .unitPrice(product.getUnitPrice())
            .currency(product.getCurrency())
            .quantity(0)
            .createdAt(product.getCreatedAt() != null ? product.getCreatedAt() : null)
            .build();
    }
    public static Product entityToDomain(ProductEntity productEntity) {
        return Product.builder()
            .id(productEntity.getId())
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .unitPrice(productEntity.getUnitPrice())
            .currency(productEntity.getCurrency())
            .quantity(productEntity.getQuantity())
            .createdAt(productEntity.getCreatedAt())
            .updatedAt(productEntity.getUpdatedAt())
            .build();
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPersistenceMapper {
    private ProductPersistenceMapper() {}
    public static ProductEntity domainToEntity(Product product) {
        return ProductEntity.builder()
            .id(null)
            .name(product.getName())
            .description(product.getDescription())
            .code(product.getCode())
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
            .code(productEntity.getCode())
            .unitPrice(productEntity.getUnitPrice())
            .currency(productEntity.getCurrency())
            .quantity(productEntity.getQuantity())
            .createdAt(productEntity.getCreatedAt())
            .updatedAt(productEntity.getUpdatedAt())
            .categories(productEntity.getCategories().stream()
                .map(CategoryPersistenceMapper::entityToDomain)
                .collect(Collectors.toSet()))
            .build();
    }
    public static List<Product> entityListToDomainList(List<ProductEntity> productEntities) {
        return productEntities.stream()
            .map(ProductPersistenceMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<Product> pageToPaginationResult(Page<ProductEntity> productEntityPage) {
        return PaginationResult.<Product>builder()
            .totalItems(productEntityPage.getTotalElements())
            .totalPages(productEntityPage.getTotalPages())
            .currentPage(productEntityPage.getNumber())
            .pageSize(productEntityPage.getSize())
            .hasNextPage(productEntityPage.hasNext())
            .items(entityListToDomainList(productEntityPage.getContent()))
            .build();
    }
}

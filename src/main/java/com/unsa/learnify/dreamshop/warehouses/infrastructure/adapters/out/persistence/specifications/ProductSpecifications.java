package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<ProductEntity> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
    public static Specification<ProductEntity> priceGreaterThanOrEqual(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("unitPrice"), minPrice);
        };
    }
    public static Specification<ProductEntity> priceLessThanOrEqual(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("unitPrice"), maxPrice);
        };
    }
    public static Specification<ProductEntity> quantityGreaterThanOrEqual(Integer minQuantity) {
        return (root, query, criteriaBuilder) -> {
            if (minQuantity == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"), minQuantity);
        };
    }
    public static Specification<ProductEntity> quantityLessThanOrEqual(Integer maxQuantity) {
        return (root, query, criteriaBuilder) -> {
            if (maxQuantity == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), maxQuantity);
        };
    }
    public static Specification<ProductEntity> categoryEquals(Integer categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                root.join("categories").get("id"),
                categoryId
            );
        };
    }
}

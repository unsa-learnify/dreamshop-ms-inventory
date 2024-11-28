package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;

import jakarta.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductQuerySpecifications {
    private static Specification<ProductEntity> contains(String fieldName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
        };
    }
    private static Specification<ProductEntity> greaterThanOrEqual(String fieldName, Comparable value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), value);
        };
    }
    private static Specification<ProductEntity> lessThanOrEqual(String fieldName, Comparable value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), value);
        };
    }
    public static Specification<ProductEntity> nameContains(String name) {
        return contains("name", name);
    }
    public static Specification<ProductEntity> descriptionContains(String description) {
        return contains("description", description);
    }
    public static Specification<ProductEntity> codeContains(String code) {
        return contains("code", code);
    }
    public static Specification<ProductEntity> priceGreaterThanOrEqual(BigDecimal minPrice) {
        return greaterThanOrEqual("unitPrice", minPrice);
    }
    public static Specification<ProductEntity> priceLessThanOrEqual(BigDecimal maxPrice) {
        return lessThanOrEqual("unitPrice", maxPrice);
    }
    public static Specification<ProductEntity> quantityGreaterThanOrEqual(Integer minQuantity) {
        return greaterThanOrEqual("quantity", minQuantity);
    }
    public static Specification<ProductEntity> quantityLessThanOrEqual(Integer maxQuantity) {
        return lessThanOrEqual("quantity", maxQuantity);
    }
    public static Specification<ProductEntity> categoryEquals(Integer categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.join("categories").get("id"), categoryId);
        };
    }
    public static Specification<ProductEntity> withCategories() {
        return (root, query, criteriaBuilder) -> {
            root.fetch("categories", JoinType.LEFT);
            return criteriaBuilder.conjunction();
        };
    }
}

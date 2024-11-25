package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;

import org.springframework.data.jpa.domain.Specification;

public class CategoryQuerySpecifications {
    public static Specification<CategoryEntity> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }
    public static Specification<CategoryEntity> descriptionContains(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null || description.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("description"), "%" + description + "%");
        };
    }
}

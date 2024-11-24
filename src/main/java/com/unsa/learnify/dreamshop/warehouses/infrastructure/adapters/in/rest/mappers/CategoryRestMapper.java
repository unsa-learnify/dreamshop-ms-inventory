package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryCreateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryUpdateRequest;

public class CategoryRestMapper {
    private CategoryRestMapper() {}
    public static CategoryResponse domainToResponse(Category category) {
        return CategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .createdAt(category.getCreatedAt())
            .updatedAt(category.getUpdatedAt())
            .build();
    }
    public static Category createRequestToDomain(CategoryCreateRequest categoryCreateRequest) {
        return Category.builder()
            .id(null)
            .name(categoryCreateRequest.name())
            .description(categoryCreateRequest.description())
            .build();
    }
    public static Category updateRequestToDomain(Integer categoryId, CategoryUpdateRequest categoryUpdateRequest) {
        return Category.builder()
            .id(categoryId)
            .name(categoryUpdateRequest.name())
            .description(categoryUpdateRequest.description())
            .build();
    }
}

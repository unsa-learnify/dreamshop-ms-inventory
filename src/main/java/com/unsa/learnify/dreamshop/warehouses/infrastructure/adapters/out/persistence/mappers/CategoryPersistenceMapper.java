package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;

import org.springframework.data.domain.Page;

import java.util.List;

public class CategoryPersistenceMapper {
    public CategoryPersistenceMapper() {}
    public static CategoryEntity domainToEntity(Category category) {
        return CategoryEntity.builder()
            .id(null)
            .name(category.getName())
            .description(category.getDescription())
            .createdAt(category.getCreatedAt() != null ? category.getCreatedAt() : null)
            .build();
    }
    public static Category entityToDomain(CategoryEntity categoryEntity) {
        return Category.builder()
            .id(categoryEntity.getId())
            .name(categoryEntity.getName())
            .description(categoryEntity.getDescription())
            .createdAt(categoryEntity.getCreatedAt())
            .updatedAt(categoryEntity.getUpdatedAt())
            .build();
    }
    public static List<Category> entityListToDomainList(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
            .map(CategoryPersistenceMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<Category> pageToPaginationResult(Page<CategoryEntity> categoryEntityPage) {
        return PaginationResult.<Category>builder()
            .totalItems(categoryEntityPage.getTotalElements())
            .totalPages(categoryEntityPage.getTotalPages())
            .currentPage(categoryEntityPage.getNumber())
            .pageSize(categoryEntityPage.getSize())
            .hasNextPage(categoryEntityPage.hasNext())
            .items(entityListToDomainList(categoryEntityPage.getContent()))
            .build();
    }
}

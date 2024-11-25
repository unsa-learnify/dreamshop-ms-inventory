package com.unsa.learnify.dreamshop.warehouses.application.ports.out;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;

import java.util.Optional;

public interface CategoryPersistencePort {
    Category createOneCategory(Category category);
    PaginationResult<Category> findCategoriesByFilters(CategoryFilters categoryFilters);
    Optional<Category> findOneCategoryById(Integer categoryId);
    Optional<Category> findOneCategoryByName(String categoryName);
    Boolean existsOneCategoryById(Integer categoryId);
    Boolean existsOneCategoryByName(String categoryName);
    void updateOneCategoryById(Integer categoryId, Category category);
    void deleteOneCategoryById(Integer categoryId);
}

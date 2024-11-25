package com.unsa.learnify.dreamshop.warehouses.application.ports.out;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {
    Category createOneCategory(Category category);
    List<Category> findCategoriesByPage(Page page);
    List<Category> findCategoriesByPageAndNameContaining(Page page, String name);
    Optional<Category> findOneCategoryById(Integer categoryId);
    Optional<Category> findOneCategoryByName(String name);
    Boolean existsOneCategoryById(Integer categoryId);
    void updateOneCategoryById(Integer categoryId, Category category);
    void deleteOneCategoryById(Integer categoryId);
}

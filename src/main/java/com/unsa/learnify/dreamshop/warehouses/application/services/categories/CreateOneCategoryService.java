package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.CreateOneCategoryServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

import org.springframework.stereotype.Service;

@Service
public class CreateOneCategoryService implements CreateOneCategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public CreateOneCategoryService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public Category execute(Category category) throws CategoryDuplicatedException {
        if (this.categoryPersistencePort.existsOneCategoryByName(category.getName())) {
            throw new CategoryDuplicatedException("Category with name '" + category.getName() + "' already exists");
        }
        return categoryPersistencePort.createOneCategory(category);
    }
}

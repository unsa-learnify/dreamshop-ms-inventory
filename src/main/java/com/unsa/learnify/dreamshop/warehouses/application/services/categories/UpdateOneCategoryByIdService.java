package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.UpdateOneCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOneCategoryByIdService implements UpdateOneCategoryByIdServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public UpdateOneCategoryByIdService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void execute(Integer categoryId, Category category) throws CategoryNotFoundException, CategoryDuplicatedException {
        if (!this.categoryPersistencePort.existsOneCategoryById(categoryId)) {
            throw new CategoryNotFoundException("Category with id '" + categoryId + "' not found");
        }
        if (category.getName() != null) {
            Optional<Category> existingCategoryByName = this.categoryPersistencePort.findOneCategoryByName(category.getName());
            if (existingCategoryByName.isPresent() && !existingCategoryByName.get().getId().equals(categoryId)) {
                throw new CategoryDuplicatedException("Category with name '" + category.getName() + "' already exists");
            }
        }
        this.categoryPersistencePort.updateOneCategoryById(categoryId, category);
    }
}

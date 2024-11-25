package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindCategoryByIdService implements FindCategoryByIdServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public FindCategoryByIdService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public Category execute(Integer categoryId) throws CategoryNotFoundException {
        Optional<Category> category = this.categoryPersistencePort.findOneCategoryById(categoryId);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with id '" + categoryId + "' not found");
        }
        return category.get();
    }
}

package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoryByNameServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindCategoryByNameService implements FindCategoryByNameServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public FindCategoryByNameService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public Category execute(String name) throws CategoryNotFoundException {
        Optional<Category> category = this.categoryPersistencePort.findOneCategoryByName(name);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with name '" + name + "' not found");
        }
        return category.get();
    }
}

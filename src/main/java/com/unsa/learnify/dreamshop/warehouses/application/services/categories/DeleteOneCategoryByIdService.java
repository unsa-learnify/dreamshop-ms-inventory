package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.DeleteOneCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class DeleteOneCategoryByIdService implements DeleteOneCategoryByIdServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public DeleteOneCategoryByIdService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void execute(Integer categoryId) throws CategoryNotFoundException {
        if (!this.categoryPersistencePort.existsOneCategoryById(categoryId)) {
            throw new CategoryNotFoundException("Category with id " + categoryId + " not found");
        }
        this.categoryPersistencePort.deleteOneCategoryById(categoryId);
    }
}

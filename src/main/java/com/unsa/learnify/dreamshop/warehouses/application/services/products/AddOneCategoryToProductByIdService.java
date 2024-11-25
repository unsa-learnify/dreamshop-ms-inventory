package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.AddOneCategoryToProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class AddOneCategoryToProductByIdService implements AddOneCategoryToProductByIdServicePort {
    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    public AddOneCategoryToProductByIdService(
        ProductPersistencePort productPersistencePort,
        CategoryPersistencePort categoryPersistencePort
    ) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void execute(Integer productId, Integer categoryId) throws ProductNotFoundException, CategoryNotFoundException, CategoryDuplicatedException {
        if (!this.productPersistencePort.existsOneProductById(productId)) {
            throw new ProductNotFoundException("Product with id " + productId + " does not exist");
        }
        if (!this.categoryPersistencePort.existsOneCategoryById(categoryId)) {
            throw new CategoryNotFoundException("Category with id " + categoryId + " does not exist");
        }
    }
}

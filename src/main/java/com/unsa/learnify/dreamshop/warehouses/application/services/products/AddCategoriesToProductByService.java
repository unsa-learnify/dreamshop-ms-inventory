package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.AddCategoriesToProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddCategoriesToProductByService implements AddCategoriesToProductByIdServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    public AddCategoriesToProductByService(
        CategoryPersistencePort categoryPersistencePort,
        ProductPersistencePort productPersistencePort
    ) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Product execute(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException, CategoryNotFoundException {
        if (!this.productPersistencePort.existsOneProductById(productId)) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        for (Integer categoryId : categoryIds) {
            if (!this.categoryPersistencePort.existsOneCategoryById(categoryId)) {
                throw new CategoryNotFoundException("Category with id " + categoryId + " not found");
            }
        }
        return this.productPersistencePort.addCategoriesToProductById(productId, categoryIds);
    }
}

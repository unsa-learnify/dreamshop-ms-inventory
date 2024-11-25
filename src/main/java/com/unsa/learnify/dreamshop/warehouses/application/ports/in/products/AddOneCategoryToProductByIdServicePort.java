package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;

public interface AddOneCategoryToProductByIdServicePort {
    void execute(Integer productId, Integer categoryId) throws ProductNotFoundException, CategoryNotFoundException, CategoryDuplicatedException;
}

package com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

public interface UpdateOneCategoryByIdServicePort {
    void execute(Integer categoryId, Category category) throws CategoryNotFoundException, CategoryDuplicatedException;
}

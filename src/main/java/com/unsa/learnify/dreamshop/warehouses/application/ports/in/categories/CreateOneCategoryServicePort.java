package com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

public interface CreateOneCategoryServicePort {
    Category execute(Category category) throws CategoryDuplicatedException;
}

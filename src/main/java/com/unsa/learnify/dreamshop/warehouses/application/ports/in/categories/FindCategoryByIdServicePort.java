package com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;

public interface FindCategoryByIdServicePort {
    Category execute(Integer categoryId) throws CategoryNotFoundException;
}

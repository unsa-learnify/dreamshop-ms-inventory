package com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;

public interface DeleteOneCategoryByIdServicePort {
    void execute(Integer categoryId) throws CategoryNotFoundException;
}

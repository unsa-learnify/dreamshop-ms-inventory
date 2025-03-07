package com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;

public interface FindCategoriesServicePort {
    PaginationResult<Category> execute(CategoryFilters categoryFilters);
}

package com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;

import java.util.List;

public interface FindCategoriesServicePort {
    List<Category> execute(CategoryFilters categoryFilters);
}

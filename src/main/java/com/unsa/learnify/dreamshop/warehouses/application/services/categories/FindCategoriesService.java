package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoriesServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;

import org.springframework.stereotype.Service;

@Service
public class FindCategoriesService implements FindCategoriesServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public FindCategoriesService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public PaginationResult<Category> execute(CategoryFilters categoryFilters) {
        return this.categoryPersistencePort.findCategoriesByFilters(categoryFilters);
    }
}

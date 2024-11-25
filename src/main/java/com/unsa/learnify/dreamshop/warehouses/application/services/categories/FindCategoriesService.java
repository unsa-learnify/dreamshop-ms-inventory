package com.unsa.learnify.dreamshop.warehouses.application.services.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoriesServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class FindCategoriesService implements FindCategoriesServicePort {
    private final CategoryPersistencePort categoryPersistencePort;
    public FindCategoriesService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public List<Category> execute(CategoryFilters categoryFilters) {
        if (!StringUtils.hasText(categoryFilters.getName())) {
            return this.categoryPersistencePort.findCategoriesByPage(categoryFilters.getPage());
        }
        return this.categoryPersistencePort.findCategoriesByPageAndNameContaining(categoryFilters.getPage(), categoryFilters.getName());
    }
}

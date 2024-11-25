package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;

import java.util.List;

public interface FindProductsWithCategoriesServicePort {
    List<Product> execute(ProductFilters productFilters);
}

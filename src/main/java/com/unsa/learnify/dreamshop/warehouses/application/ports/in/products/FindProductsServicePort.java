package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;

public interface FindProductsServicePort {
    PaginationResult<Product> execute(ProductFilters productFilters);
}

package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

public interface FindProductWithCategoriesByIdServicePort {
    Product execute(Integer productId) throws ProductNotFoundException;
}

package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

public interface CreateOneProductServicePort {
    Product execute(Product product) throws ProductDuplicatedException;
}

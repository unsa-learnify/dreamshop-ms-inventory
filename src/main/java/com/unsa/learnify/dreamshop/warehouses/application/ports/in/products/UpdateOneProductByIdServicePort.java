package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

public interface UpdateOneProductByIdServicePort {
    void execute(Integer productId, Product product) throws ProductNotFoundException, ProductDuplicatedException;
}

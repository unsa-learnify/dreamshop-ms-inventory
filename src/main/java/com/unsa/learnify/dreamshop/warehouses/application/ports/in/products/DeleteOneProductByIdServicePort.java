package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;

public interface DeleteOneProductByIdServicePort {
    void execute(Integer productId) throws ProductNotFoundException;
}

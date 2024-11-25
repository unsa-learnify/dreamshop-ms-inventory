package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;

import java.util.List;

public interface AddCategoriesToProductByIdServicePort {
    void execute(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException, CategoryNotFoundException;
}

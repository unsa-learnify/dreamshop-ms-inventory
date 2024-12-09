package com.unsa.learnify.dreamshop.warehouses.application.ports.out;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {
    Product createOneProduct(Product product);
    Product addCategoriesToProductById(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException;
    PaginationResult<Product> findProductsByFilters(ProductFilters productFilters);
    Optional<Product> findOneProductById(Integer productId);
    Optional<Product> findOneProductByName(String productName);
    Optional<Product> findOneProductByCode(String productCode);
    Boolean existsOneProductById(Integer productId);
    Boolean existsOneProductByName(String productName);
    Boolean existsOneProductByCode(String productCode);
    void updateOneProductById(Integer productId, Product product);
    void deleteOneProductById(Integer productId);
    void deleteCategoriesToProductById(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException;
}

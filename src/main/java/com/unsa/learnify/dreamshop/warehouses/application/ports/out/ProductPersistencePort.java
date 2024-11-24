package com.unsa.learnify.dreamshop.warehouses.application.ports.out;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {
    Product createOneProduct(Product product);
    List<Product> findProductsByFilters(ProductFilters productFilters);
    Optional<Product> findOneProductById(Integer productId);
    Optional<Product> findOneProductByName(String name);
    Boolean existsOneProductById(Integer productId);
    void updateOneProductById(Integer productId, Product product);
    void deleteOneProductById(Integer productId);
}

package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductsServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProductsService implements FindProductsServicePort {
    private final ProductPersistencePort productPersistencePort;
    public FindProductsService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public List<Product> execute(ProductFilters productFilters) {
        return this.productPersistencePort.findProductsByFilters(productFilters);
    }
}

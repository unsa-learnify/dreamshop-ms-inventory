package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductByNameServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindProductByNameService implements FindProductByNameServicePort {
    private final ProductPersistencePort productPersistencePort;
    public FindProductByNameService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Product execute(String name) throws ProductNotFoundException {
        Optional<Product> product = this.productPersistencePort.findOneProductByName(name);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with name '" + name + "' not found");
        }
        return product.get();
    }
}

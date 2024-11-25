package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindProductByIdService implements FindProductByIdServicePort {
    private final ProductPersistencePort productPersistencePort;
    public FindProductByIdService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Product execute(Integer productId) throws ProductNotFoundException {
        Optional<Product> product = this.productPersistencePort.findOneProductById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        return product.get();
    }
}

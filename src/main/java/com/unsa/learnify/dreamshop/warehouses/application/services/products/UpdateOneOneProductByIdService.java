package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.UpdateOneProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOneOneProductByIdService implements UpdateOneProductByIdServicePort {
    private final ProductPersistencePort productPersistencePort;
    public UpdateOneOneProductByIdService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public void execute(Integer productId, Product product) throws ProductNotFoundException, ProductDuplicatedException {
        if (!this.productPersistencePort.existsOneProductById(productId)) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        if (product.getName() != null) {
            Optional<Product> existingProductByName = this.productPersistencePort.findOneProductByName(product.getName());
            if (existingProductByName.isPresent() && !existingProductByName.get().getId().equals(productId)) {
                throw new ProductDuplicatedException("Product with name '" + product.getName() + "' already exists");
            }
        }
        this.productPersistencePort.updateOneProductById(productId, product);
    }
}

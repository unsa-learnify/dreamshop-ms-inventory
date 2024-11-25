package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.CreateOneProductServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateOneProductService implements CreateOneProductServicePort {
    private final ProductPersistencePort productPersistencePort;
    public CreateOneProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Product execute(Product product) throws ProductDuplicatedException {
        Optional<Product> existingProduct = this.productPersistencePort.findOneProductByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new ProductDuplicatedException("Product with name '" + product.getName() + "' already exists");
        }
        return this.productPersistencePort.createOneProduct(product);
    }
}

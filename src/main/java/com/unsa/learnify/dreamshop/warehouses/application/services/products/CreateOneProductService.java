package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.CreateOneProductServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;

@Service
public class CreateOneProductService implements CreateOneProductServicePort {
    private final ProductPersistencePort productPersistencePort;
    public CreateOneProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public Product execute(Product product) throws ProductDuplicatedException {
        if (this.productPersistencePort.existsOneProductByName(product.getName())) {
            throw new ProductDuplicatedException("Product with name '" + product.getName() + "' already exists");
        }
        if (this.productPersistencePort.existsOneProductByCode(product.getCode())) {
            throw new ProductDuplicatedException("Product with code '" + product.getCode() + "' already exists");
        }
        return this.productPersistencePort.createOneProduct(product);
    }
}

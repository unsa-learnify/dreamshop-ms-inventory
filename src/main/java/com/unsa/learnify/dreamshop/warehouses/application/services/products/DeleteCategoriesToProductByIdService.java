package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.DeleteCategoriesToProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCategoriesToProductByIdService implements DeleteCategoriesToProductByIdServicePort {
    private final ProductPersistencePort productPersistencePort;
    public DeleteCategoriesToProductByIdService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public void execute(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException {
        if (!this.productPersistencePort.existsOneProductById(productId)) {
            throw new ProductNotFoundException("Product with id " + productId + " does not exist");
        }
        this.productPersistencePort.deleteCategoriesToProductById(productId, categoryIds);
    }
}

package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.DeleteOneProductByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteOneProductByIdService implements DeleteOneProductByIdServicePort {
    private final ProductPersistencePort productPersistencePort;
    public DeleteOneProductByIdService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public void execute(Integer productId) throws ProductNotFoundException {
        if (!this.productPersistencePort.existsOneProductById(productId)) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        this.productPersistencePort.deleteOneProductById(productId);
    }
}

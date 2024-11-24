package com.unsa.learnify.dreamshop.warehouses.domain.exceptions;

public class ProductDuplicatedException extends Exception {
    public ProductDuplicatedException(String message) {
        super(message);
    }
}

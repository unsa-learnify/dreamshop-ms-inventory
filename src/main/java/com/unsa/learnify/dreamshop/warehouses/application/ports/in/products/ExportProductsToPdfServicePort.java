package com.unsa.learnify.dreamshop.warehouses.application.ports.in.products;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ExportProductsToPdfServicePort {
    InputStream execute(List<Product> products) throws IOException;
}

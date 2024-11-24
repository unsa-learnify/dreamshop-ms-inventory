package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.DeleteOneCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class DeleteOneCategoryByIdRestController {
    private final DeleteOneCategoryByIdServicePort deleteOneCategoryByIdServicePort;
    public DeleteOneCategoryByIdRestController(DeleteOneCategoryByIdServicePort deleteOneCategoryByIdServicePort) {
        this.deleteOneCategoryByIdServicePort = deleteOneCategoryByIdServicePort;
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteOneCategoryById(@PathVariable Integer categoryId) throws CategoryNotFoundException {
        this.deleteOneCategoryByIdServicePort.execute(categoryId);
        return ResponseEntity.noContent().build();
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.UpdateOneCategoryByIdServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryUpdateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class UpdateOneCategoryByIdRestController {
    private final UpdateOneCategoryByIdServicePort updateOneCategoryByIdServicePort;
    public UpdateOneCategoryByIdRestController(UpdateOneCategoryByIdServicePort updateOneCategoryByIdServicePort) {
        this.updateOneCategoryByIdServicePort = updateOneCategoryByIdServicePort;
    }
    @PatchMapping("/{categoryId}")
    public ResponseEntity<Void> updateOneCategoryById(
        @PathVariable Integer categoryId,
        @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest
    ) throws CategoryDuplicatedException, CategoryNotFoundException {
        Category category = CategoryRestMapper.updateRequestToDomain(categoryId, categoryUpdateRequest);
        this.updateOneCategoryByIdServicePort.execute(categoryId, category);
        return ResponseEntity.noContent().build();
    }
}

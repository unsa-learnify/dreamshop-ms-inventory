package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.services.categories.FindCategoryByIdService;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class FindCategoryByIdRestController {
    private final FindCategoryByIdService findCategoryByIdServicePort;
    public FindCategoryByIdRestController(FindCategoryByIdService findCategoryByIdServicePort) {
        this.findCategoryByIdServicePort = findCategoryByIdServicePort;
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Integer categoryId) throws CategoryNotFoundException {
        Category category = this.findCategoryByIdServicePort.execute(categoryId);
        return ResponseEntity.ok(CategoryRestMapper.domainToResponse(category));
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.FindCategoryByNameServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class FindCategoryByNameRestController {
    private final FindCategoryByNameServicePort findCategoryByNameServicePort;
    public FindCategoryByNameRestController(FindCategoryByNameServicePort findCategoryByNameServicePort) {
        this.findCategoryByNameServicePort = findCategoryByNameServicePort;
    }
    @GetMapping("/name")
    public ResponseEntity<CategoryResponse> findCategoryByName(@RequestParam String query) throws CategoryNotFoundException {
        Category category = this.findCategoryByNameServicePort.execute(query);
        return ResponseEntity.ok(CategoryRestMapper.domainToResponse(category));
    }
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.categories;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.categories.CreateOneCategoryServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryCreateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories.CategoryResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers.CategoryRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/categories")
public class CreateOneCategoryRestController {
    private final CreateOneCategoryServicePort createOneCategoryServicePort;
    public CreateOneCategoryRestController(CreateOneCategoryServicePort createOneCategoryServicePort) {
        this.createOneCategoryServicePort = createOneCategoryServicePort;
    }
    @PostMapping
    public ResponseEntity<CategoryResponse> createOneCategory(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest) throws CategoryDuplicatedException {
        Category category = CategoryRestMapper.createRequestToDomain(categoryCreateRequest);
        Category savedCategory = this.createOneCategoryServicePort.execute(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(CategoryRestMapper.domainToResponse(savedCategory));
    }
}

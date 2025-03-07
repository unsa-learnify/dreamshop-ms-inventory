package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.implementations;

import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.CategoryFilters;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers.CategoryPersistenceMapper;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories.CategoryJpaRepository;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications.CategoryQuerySpecifications;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryMySQLPersistenceAdapter implements CategoryPersistencePort {
    private final CategoryJpaRepository categoryJpaRepository;
    public CategoryMySQLPersistenceAdapter(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }
    @Override
    @Transactional
    public Category createOneCategory(Category category) {
        CategoryEntity categoryEntity = CategoryPersistenceMapper.domainToEntity(category);
        CategoryEntity savedCategoryEntity = categoryJpaRepository.save(categoryEntity);
        return CategoryPersistenceMapper.entityToDomain(savedCategoryEntity);
    }
    @Override
    public PaginationResult<Category> findCategoriesByFilters(CategoryFilters categoryFilters) {
        Specification<CategoryEntity> specification = Specification.where(null);
        if (categoryFilters.getName() != null && !categoryFilters.getName().isEmpty()) {
            specification = specification.and(CategoryQuerySpecifications.nameContains(categoryFilters.getName()));
        }
        if (categoryFilters.getDescription() != null && !categoryFilters.getDescription().isEmpty()) {
            specification = specification.and(CategoryQuerySpecifications.descriptionContains(categoryFilters.getDescription()));
        }
        Pageable pageable = PageRequest.of(categoryFilters.getPage().getNumber(), categoryFilters.getPage().getSize(), Sort.by(Sort.Direction.ASC, "id"));
        Page<CategoryEntity> categoryEntities = categoryJpaRepository.findAll(specification, pageable);
        return CategoryPersistenceMapper.pageToPaginationResult(categoryEntities);
    }
    @Override
    public Optional<Category> findOneCategoryById(Integer categoryId) {
        return this.categoryJpaRepository.findById(categoryId)
            .map(CategoryPersistenceMapper::entityToDomain);
    }
    public Optional<Category> findOneCategoryByName(String categoryName) {
        return this.categoryJpaRepository.findByNameIgnoreCase(categoryName)
            .map(CategoryPersistenceMapper::entityToDomain);
    }
    @Override
    public Boolean existsOneCategoryById(Integer categoryId) {
        return this.categoryJpaRepository.existsById(categoryId);
    }
    @Override
    public Boolean existsOneCategoryByName(String name) {
        return this.categoryJpaRepository.existsByNameIgnoreCase(name);
    }
    @Override
    @Transactional
    public void updateOneCategoryById(Integer categoryId, Category category) {
        Optional<CategoryEntity> categoryEntity = this.categoryJpaRepository.findById(categoryId);
        if (categoryEntity.isEmpty()) {
            return;
        }
        CategoryEntity categoryEntityToUpdate = categoryEntity.get();
        if (category.getName() != null) {
            categoryEntityToUpdate.setName(category.getName());
        }
        if (category.getDescription() != null) {
            categoryEntityToUpdate.setDescription(category.getDescription());
        }
        this.categoryJpaRepository.save(categoryEntityToUpdate);
    }
    @Override
    @Transactional
    public void deleteOneCategoryById(Integer categoryId) {
        this.categoryJpaRepository.deleteById(categoryId);
    }
}

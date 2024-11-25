package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.implementations;

import com.unsa.learnify.dreamshop.warehouses.application.ports.out.CategoryPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Category;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Page;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers.CategoryPersistenceMapper;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories.CategoryJpaRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<Category> findCategoriesByPage(Page page) {
        Pageable pageable = PageRequest.of(page.getNumber(), page.getSize(), Sort.by(Sort.Direction.ASC, "id"));
        return this.categoryJpaRepository.findAllBy(pageable)
            .stream()
            .map(CategoryPersistenceMapper::entityToDomain)
            .toList();
    }
    @Override
    public List<Category> findCategoriesByPageAndNameContaining(Page page, String name) {
        Pageable pageable = PageRequest.of(page.getNumber(), page.getSize(), Sort.by(Sort.Direction.ASC, "id"));
        return this.categoryJpaRepository.findByNameContaining(name, pageable)
            .stream()
            .map(CategoryPersistenceMapper::entityToDomain)
            .toList();
    }
    @Override
    public Optional<Category> findOneCategoryById(Integer categoryId) {
        return this.categoryJpaRepository.findById(categoryId)
            .map(CategoryPersistenceMapper::entityToDomain);
    }
    @Override
    public Optional<Category> findOneCategoryByName(String name) {
        return this.categoryJpaRepository.findByName(name)
            .map(CategoryPersistenceMapper::entityToDomain);
    }
    @Override
    public Boolean existsOneCategoryById(Integer categoryId) {
        return this.categoryJpaRepository.existsById(categoryId);
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

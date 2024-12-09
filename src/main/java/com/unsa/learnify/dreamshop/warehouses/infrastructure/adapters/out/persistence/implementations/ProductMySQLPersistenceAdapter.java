package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.implementations;

import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers.ProductPersistenceMapper;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories.CategoryJpaRepository;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories.ProductJpaRepository;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications.ProductQuerySpecifications;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications.SpecificationUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMySQLPersistenceAdapter implements ProductPersistencePort {
    private final CategoryJpaRepository categoryJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    public ProductMySQLPersistenceAdapter(
        CategoryJpaRepository categoryJpaRepository,
        ProductJpaRepository productJpaRepository
    ) {
        this.categoryJpaRepository = categoryJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }
    @Override
    @Transactional
    public Product createOneProduct(Product product) {
        ProductEntity productEntity = ProductPersistenceMapper.domainToEntity(product);
        ProductEntity savedProductEntity = this.productJpaRepository.save(productEntity);
        return ProductPersistenceMapper.entityToDomain(savedProductEntity);
    }
    @Override
    @Transactional
    public Product addCategoriesToProductById(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException {
        Optional<ProductEntity> productEntity = this.productJpaRepository.findById(productId);
        if (productEntity.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        ProductEntity productEntityToAdd = productEntity.get();
        List<CategoryEntity> categoryEntities = this.categoryJpaRepository.findAllByIdIn(categoryIds);
        productEntityToAdd.getCategories().addAll(categoryEntities);
        ProductEntity savedProductEntity = this.productJpaRepository.save(productEntityToAdd);
        return ProductPersistenceMapper.entityToDomain(savedProductEntity);
    }
    @Override
    public PaginationResult<Product> findProductsByFilters(ProductFilters productFilters) {
        Specification<ProductEntity> specification = Specification.where(null);
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.nameContains(productFilters.getName()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.descriptionContains(productFilters.getDescription()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.codeContains(productFilters.getCode()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.priceGreaterThanOrEqual(productFilters.getMinPrice()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.priceLessThanOrEqual(productFilters.getMaxPrice()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.quantityGreaterThanOrEqual(productFilters.getMinQuantity()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.quantityLessThanOrEqual(productFilters.getMaxQuantity()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.categoryEquals(productFilters.getCategoryId()));
        specification = SpecificationUtils.applySpecificationIfNotNull(specification, ProductQuerySpecifications.withCategories());
        Pageable pageable = PageRequest.of(
            productFilters.getPage().getNumber(),
            productFilters.getPage().getSize(),
            Sort.by(Sort.Direction.ASC, "id")
        );
        Page<ProductEntity> productEntities = this.productJpaRepository.findAll(specification, pageable);
        return ProductPersistenceMapper.pageToPaginationResult(productEntities);
    }
    @Override
    public Optional<Product> findOneProductById(Integer productId) {
        return this.productJpaRepository.findByIdWithCategories(productId)
            .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Optional<Product> findOneProductByName(String productName) {
        return this.productJpaRepository.findByNameIgnoreCase(productName)
            .map(ProductPersistenceMapper::entityWitNoCategoriesToDomain);
    }
    @Override
    public Optional<Product> findOneProductByCode(String productCode) {
        return this.productJpaRepository.findByCode(productCode)
            .map(ProductPersistenceMapper::entityWitNoCategoriesToDomain);
    }
    @Override
    public Boolean existsOneProductById(Integer productId) {
        return this.productJpaRepository.existsById(productId);
    }
    @Override
    public Boolean existsOneProductByName(String productName) {
        return this.productJpaRepository.existsByNameIgnoreCase(productName);
    }
    @Override
    public Boolean existsOneProductByCode(String productCode) {
        return this.productJpaRepository.existsByCode(productCode);
    }
    @Override
    @Transactional
    public void updateOneProductById(Integer productId, Product product) {
        this.productJpaRepository.findById(productId)
            .ifPresent(productEntityToUpdate -> {
                Optional.ofNullable(product.getName())
                    .filter(name -> !name.equals(productEntityToUpdate.getName()))
                    .ifPresent(productEntityToUpdate::setName);
                Optional.ofNullable(product.getDescription())
                    .filter(description -> !description.equals(productEntityToUpdate.getDescription()))
                    .ifPresent(productEntityToUpdate::setDescription);
                Optional.ofNullable(product.getUnitPrice())
                    .filter(unitPrice -> !unitPrice.equals(productEntityToUpdate.getUnitPrice()))
                    .ifPresent(productEntityToUpdate::setUnitPrice);
                Optional.ofNullable(product.getCurrency())
                    .filter(currency -> !currency.equals(productEntityToUpdate.getCurrency()))
                    .ifPresent(productEntityToUpdate::setCurrency);
                Optional.ofNullable(product.getQuantity())
                    .filter(quantity -> !quantity.equals(productEntityToUpdate.getQuantity()))
                    .ifPresent(productEntityToUpdate::setQuantity);
                this.productJpaRepository.save(productEntityToUpdate);
            });
    }
    @Override
    @Transactional
    public void deleteOneProductById(Integer productId) {
        this.productJpaRepository.deleteById(productId);
    }
    @Override
    @Transactional
    public void deleteCategoriesToProductById(Integer productId, List<Integer> categoryIds) throws ProductNotFoundException {
        Optional<ProductEntity> optionalProductEntity = this.productJpaRepository.findById(productId);
        if (optionalProductEntity.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        ProductEntity productEntity = optionalProductEntity.get();
        List<CategoryEntity> categoryEntitiesToRemove = productEntity.getCategories().stream()
            .filter(categoryEntity -> categoryIds.contains(categoryEntity.getId()))
            .toList();
        categoryEntitiesToRemove.forEach(productEntity.getCategories()::remove);
        this.productJpaRepository.save(productEntity);
    }
}

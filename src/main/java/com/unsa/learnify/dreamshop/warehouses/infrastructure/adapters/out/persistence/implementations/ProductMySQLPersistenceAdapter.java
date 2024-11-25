package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.implementations;

import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers.ProductPersistenceMapper;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories.ProductJpaRepository;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications.ProductSpecifications;

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
    private final ProductJpaRepository productJpaRepository;
    public ProductMySQLPersistenceAdapter(ProductJpaRepository productJpaRepository) {
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
    public List<Product> findProductsByFilters(ProductFilters productFilters) {
        Specification<ProductEntity> specification = Specification.where(null);
        if (productFilters.getName() != null) {
            specification = specification.and(ProductSpecifications.nameContains(productFilters.getName()));
        }
        if (productFilters.getCategoryId() != null) {
            specification = specification.and(ProductSpecifications.categoryEquals(productFilters.getCategoryId()));
        }
        if (productFilters.getMinPrice() != null) {
            specification = specification.and(ProductSpecifications.priceGreaterThanOrEqual(productFilters.getMinPrice()));
        }
        if (productFilters.getMaxPrice() != null) {
            specification = specification.and(ProductSpecifications.priceLessThanOrEqual(productFilters.getMaxPrice()));
        }
        if (productFilters.getMinQuantity() != null) {
            specification = specification.and(ProductSpecifications.quantityGreaterThanOrEqual(productFilters.getMinQuantity()));
        }
        if (productFilters.getMaxQuantity() != null) {
            specification = specification.and(ProductSpecifications.quantityLessThanOrEqual(productFilters.getMaxQuantity()));
        }
        Pageable pageable = PageRequest.of(
            productFilters.getPage().getNumber(),
            productFilters.getPage().getSize(),
            Sort.by(Sort.Direction.ASC, "id")
        );
        return this.productJpaRepository.findAll(specification, pageable)
            .getContent()
            .stream()
            .map(ProductPersistenceMapper::entityToDomain)
            .toList();
    }
    @Override
    public Optional<Product> findOneProductById(Integer productId) {
        return this.productJpaRepository.findById(productId)
            .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Optional<Product> findOneProductByName(String name) {
        return this.productJpaRepository.findByName(name)
            .map(ProductPersistenceMapper::entityToDomain);
    }
    @Override
    public Boolean existsOneProductById(Integer productId) {
        return this.productJpaRepository.existsById(productId);
    }
    @Override
    @Transactional
    public void updateOneProductById(Integer productId, Product product) {
        Optional<ProductEntity> productEntity = this.productJpaRepository.findById(productId);
        if (productEntity.isEmpty()) {
            return;
        }
        ProductEntity productEntityToUpdate = productEntity.get();
        if (product.getName() != null) {
            productEntityToUpdate.setName(product.getName());
        }
        if (product.getDescription() != null) {
            productEntityToUpdate.setDescription(product.getDescription());
        }
        if (product.getUnitPrice() != null) {
            productEntityToUpdate.setUnitPrice(product.getUnitPrice());
        }
        if (product.getCurrency() != null) {
            productEntityToUpdate.setCurrency(product.getCurrency());
        }
        if (product.getQuantity() != null) {
            productEntityToUpdate.setQuantity(product.getQuantity());
        }
        this.productJpaRepository.save(productEntityToUpdate);
    }
    @Override
    @Transactional
    public void deleteOneProductById(Integer productId) {
        this.productJpaRepository.deleteById(productId);
    }
}

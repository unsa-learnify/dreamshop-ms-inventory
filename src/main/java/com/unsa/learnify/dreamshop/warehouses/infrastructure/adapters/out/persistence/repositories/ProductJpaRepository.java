package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {
    @Query("select p from product p left join fetch p.categories where p.id = :id")
    Optional<ProductEntity> findByIdWithCategories(@Param("id") Integer id);
    Optional<ProductEntity> findByNameIgnoreCase(String name);
    Optional<ProductEntity> findByCode(String code);
    Boolean existsByNameIgnoreCase(String name);
    Boolean existsByCode(String code);
}

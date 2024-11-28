package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {
    List<CategoryEntity> findAllByIdIn(List<Integer> categoryIds);
    Optional<CategoryEntity> findByNameIgnoreCase(String name);
    Boolean existsByNameIgnoreCase(String name);
}

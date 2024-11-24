package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.CategoryEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {
    Slice<CategoryEntity> findAllBy(Pageable pageable);
    @Query("select c from category c where lower(c.name) = lower(:name)")
    Optional<CategoryEntity> findByName(@Param("name") String name);
    @Query("select c from category c where lower(c.name) like lower(concat('%', :name, '%') )")
    Slice<CategoryEntity> findByNameContaining(@Param("name") String name, Pageable pageable);
}

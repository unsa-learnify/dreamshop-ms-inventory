package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.repositories;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.ProductEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {
    Slice<ProductEntity> findAllBy(Pageable pageable);
    @Query("select p from product p where lower(p.name) = lower(:name)")
    Optional<ProductEntity> findByName(@Param("name") String name);
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {
    public static <T> Specification<T> applySpecificationIfNotNull(Specification<T> specification, Specification<T> condition) {
        return condition == null ? specification : specification.and(condition);
    }
}

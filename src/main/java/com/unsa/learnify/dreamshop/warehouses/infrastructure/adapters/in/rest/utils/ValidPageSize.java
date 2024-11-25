package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PageSizeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPageSize {
    String message() default "Page size must be a numeric value between 1 and 100";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PositiveIntegerValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPositiveInteger {
    String message() default "Value must be a valid positive integer (0 or greater)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

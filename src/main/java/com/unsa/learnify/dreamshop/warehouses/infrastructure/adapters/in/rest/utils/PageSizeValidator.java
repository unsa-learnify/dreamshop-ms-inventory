package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageSizeValidator implements ConstraintValidator<ValidPageSize, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= 1 && intValue <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

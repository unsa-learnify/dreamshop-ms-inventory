package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Currency;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {
    private String validCurrencies;
    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        validCurrencies = Arrays.stream(Currency.values())
            .map(Currency::getCode)
            .collect(Collectors.joining(", "));
    }
    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext context) {
        boolean isValid = Arrays.stream(Currency.values())
            .anyMatch(currency -> currency.getCode().equalsIgnoreCase(currencyCode));
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Invalid currency code. Valid values are: " + validCurrencies
            ).addConstraintViolation();
        }
        return isValid;
    }
}

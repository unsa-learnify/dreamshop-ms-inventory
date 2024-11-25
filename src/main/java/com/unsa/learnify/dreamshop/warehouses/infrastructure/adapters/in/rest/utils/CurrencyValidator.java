package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Currency;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {
    private Set<String> validCurrencyCodes;
    private String validCurrenciesMessage;
    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        validCurrencyCodes = Arrays.stream(Currency.values())
            .map(Currency::getCode)
            .map(String::toUpperCase)
            .collect(Collectors.toSet());
        validCurrenciesMessage = String.join(", ", validCurrencyCodes);
    }
    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext context) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return true;
        }
        boolean isValid = validCurrencyCodes.contains(currencyCode.toUpperCase());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Invalid currency code. Valid values are: " + validCurrenciesMessage
            ).addConstraintViolation();
        }
        return isValid;
    }
}

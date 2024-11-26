package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.converters;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Currency;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, String> {
    @Override
    public String convertToDatabaseColumn(Currency currency) {
        return (currency == null) ? Currency.DOLLAR.getCode() : currency.getCode();
    }
    @Override
    public Currency convertToEntityAttribute(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return Currency.DOLLAR;
        }
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equals(currencyCode)) {
                return currency;
            }
        }
        return Currency.DOLLAR;
    }
}

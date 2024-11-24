package com.unsa.learnify.dreamshop.warehouses.domain.models;

import lombok.Getter;

@Getter
public enum Currency {
    DOLLAR("USD", "United States Dollar"),
    EURO("EUR", "Euro"),
    NUEVO_SOL("PEN", "Peruvian Nuevo Sol");
    private final String code;
    private final String description;
    Currency(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
    @Override
    public String toString() {
        return code + " - " + description;
    }
}

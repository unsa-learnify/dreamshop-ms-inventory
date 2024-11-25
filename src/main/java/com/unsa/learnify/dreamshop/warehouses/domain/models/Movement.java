package com.unsa.learnify.dreamshop.warehouses.domain.models;

import lombok.Getter;

@Getter
public enum Movement {
    INBOUND("IN", "Product entry to the warehouse"),
    OUTBOUND("OUT", "Product to the warehouse");
    private final String code;
    private final String description;
    Movement(String code, String description) {
        this.code = code;
        this.description = description;
    }
    @Override
    public String toString() {
        return code + " - " + description;
    }
}

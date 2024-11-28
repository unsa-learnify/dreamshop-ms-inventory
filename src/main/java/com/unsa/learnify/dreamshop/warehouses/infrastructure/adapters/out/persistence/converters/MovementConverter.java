package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.converters;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Movement;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MovementConverter implements AttributeConverter<Movement, String> {
    @Override
    public String convertToDatabaseColumn(Movement movement) {
        return (movement == null) ? Movement.INBOUND.getCode() : movement.getCode();
    }
    @Override
    public Movement convertToEntityAttribute(String movementCode) {
        if (movementCode == null || movementCode.isEmpty()) {
            return Movement.INBOUND;
        }
        try {
            return Movement.valueOf(movementCode);
        } catch (IllegalArgumentException e) {
            return Movement.INBOUND;
        }
    }
}

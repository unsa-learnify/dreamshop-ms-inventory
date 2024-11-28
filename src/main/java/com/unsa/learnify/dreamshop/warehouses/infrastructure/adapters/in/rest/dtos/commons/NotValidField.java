package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons;

import org.springframework.validation.FieldError;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Invalid field during data validation.")
public record NotValidField(
    @Schema(description = "Name of the field that is invalid", example = "name")
    String field,
    @Schema(description = "Error message associated with the invalid field", example = "Field cannot be empty")
    String message
) {
    public NotValidField(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}

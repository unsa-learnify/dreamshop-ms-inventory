package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.categories;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPageSize;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.ValidPositiveInteger;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryPageRequest {
    @NotBlank
    @ValidPositiveInteger
    private String page = "0";
    @NotBlank
    @ValidPageSize
    private String size = "10";
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Name must contain only letters, numbers, and spaces")
    private String name;
}

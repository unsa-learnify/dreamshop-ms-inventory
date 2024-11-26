package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddCategoriesRequest {
    @NotNull(message = "Category ids list cannot be null")
    @NotEmpty(message = "Category ids list cannot be empty")
    @Size(max = 25, message = "Category ids length cannot exceed 25")
    private List<@Min(value = 1, message = "Category id must be greater than 0") Integer> categoryIds;
}

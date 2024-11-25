package com.unsa.learnify.dreamshop.warehouses.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteFilters {
    private Page page;
    private String title;
    private Movement movement;
    private Integer minAmount;
    private Integer maxAmount;
    private Integer productId;
}

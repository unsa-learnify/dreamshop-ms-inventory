package com.unsa.learnify.dreamshop.warehouses.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private Long id;
    private String title;
    private String description;
    private Movement movement;
    private Integer amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Product product;
}

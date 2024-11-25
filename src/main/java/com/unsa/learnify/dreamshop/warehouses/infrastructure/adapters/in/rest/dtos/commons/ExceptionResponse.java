package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String path;
    private String method;
    private Integer statusCode;
    private String statusMessage;
    private String message;
}

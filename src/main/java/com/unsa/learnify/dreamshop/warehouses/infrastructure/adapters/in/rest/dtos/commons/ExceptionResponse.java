package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response for an exception that occurred during the execution of a request.")
public class ExceptionResponse {
    @Schema(
        description = "Path of the request that triggered the exception.",
        example = "/api/v1/categories"
    )
    private String path;
    @Schema(
        description = "HTTP method used in the request (e.g., GET, POST, PUT, DELETE).",
        example = "GET"
    )
    private String method;
    @Schema(
        description = "HTTP status code associated with the exception.",
        example = "404"
    )
    private Integer statusCode;
    @Schema(
        description = "Status message associated with the HTTP status code.",
        example = "NOT_FOUND"
    )
    private String statusMessage;
    @Schema(
        description = "Exception message describing the error.",
        example = "Category not found"
    )
    private String message;
}

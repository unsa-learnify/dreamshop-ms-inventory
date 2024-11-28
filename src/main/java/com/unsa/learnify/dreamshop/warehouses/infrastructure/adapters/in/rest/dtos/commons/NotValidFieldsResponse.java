package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response for invalid fields during request validation.")
public class NotValidFieldsResponse {
    @Schema(description = "Path of the request that triggered the validation errors", example = "/api/v1/categories")
    private String path;
    @Schema(description = "HTTP method used in the request (e.g., GET, POST, PUT, DELETE)", example = "POST")
    private String method;
    @Schema(description = "HTTP status code associated with the validation error", example = "400")
    private Integer statusCode;
    @Schema(description = "HTTP status message associated with the validation error", example = "BAD_REQUEST")
    private String statusMessage;
    @Schema(description = "List of invalid fields and associated error messages", example = "[{\"field\": \"name\", \"message\": \"Field cannot be empty\"}]")
    private List<NotValidField> notValidFields;
}

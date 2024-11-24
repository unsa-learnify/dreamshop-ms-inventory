package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.advices;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductRestAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> productNotFoundException(
        ProductNotFoundException productNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .statusMessage(HttpStatus.NOT_FOUND.name())
            .message(productNotFoundException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(ProductDuplicatedException.class)
    public ResponseEntity<ExceptionResponse> productDuplicatedException(
        ProductDuplicatedException productDuplicatedException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.CONFLICT.value())
            .statusMessage(HttpStatus.CONFLICT.name())
            .message(productDuplicatedException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }
}

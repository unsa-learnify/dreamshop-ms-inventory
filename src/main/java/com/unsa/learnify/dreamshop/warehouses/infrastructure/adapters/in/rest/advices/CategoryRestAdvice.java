package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.advices;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryDuplicatedException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CategoryNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryRestAdvice {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundException(
        CategoryNotFoundException categoryNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .statusMessage(HttpStatus.NOT_FOUND.name())
            .message(categoryNotFoundException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(CategoryDuplicatedException.class)
    public ResponseEntity<ExceptionResponse> categoryDuplicatedException(
        CategoryDuplicatedException categoryDuplicatedException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.CONFLICT.value())
            .statusMessage(HttpStatus.CONFLICT.name())
            .message(categoryDuplicatedException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }
}

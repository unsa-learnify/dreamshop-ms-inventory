package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.advices;

import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.ExceptionResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.NotValidField;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.NotValidFieldsResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class ValidationRestAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NotValidFieldsResponse> methodArgumentNotValidException(
        MethodArgumentNotValidException methodArgumentNotValidException,
        HttpServletRequest httpServletRequest
    ) {
        List<NotValidField> notValidFields = methodArgumentNotValidException.getFieldErrors().stream().map(NotValidField::new).toList();
        NotValidFieldsResponse notValidFieldsResponse = NotValidFieldsResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .statusMessage(HttpStatus.BAD_REQUEST.name())
            .notValidFields(notValidFields)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notValidFieldsResponse);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException exception,
        HttpServletRequest request
    ) {
        String field = exception.getName();
        String requiredType = getExpectedTypeMessage(exception.getRequiredType());
        String errorMessage = String.format("Invalid value for parameter '%s'. Expected %s.", field, requiredType);
        ExceptionResponse response = ExceptionResponse.builder()
            .path(request.getRequestURI())
            .method(request.getMethod())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .statusMessage(HttpStatus.BAD_REQUEST.name())
            .message(errorMessage)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpRequestMethodNotSupportedException.getMethod())
            .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
            .statusMessage(HttpStatus.METHOD_NOT_ALLOWED.name())
            .message(httpRequestMethodNotSupportedException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionResponse);
    }
    private String getExpectedTypeMessage(Class<?> requiredType) {
        return switch (requiredType != null ? requiredType.getSimpleName() : "Unknown") {
            case "Integer" -> "an integer";
            case "Double" -> "a decimal number";
            case "Boolean" -> "a boolean (true/false)";
            case "String" -> "a string";
            default -> "a value of type " + (requiredType != null ? requiredType.getSimpleName() : "unknown");
        };
    }
}

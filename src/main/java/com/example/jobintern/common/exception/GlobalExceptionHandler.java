package com.example.jobintern.common.exception;

import com.example.jobintern.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(jobinternException.class)
    public ResponseEntity<ApiResponse<?>> handlerGlobalException(jobinternException e) {
        return new ResponseEntity<>(ApiResponse.error(e.getMessage()), e.getStatus());
    }
}

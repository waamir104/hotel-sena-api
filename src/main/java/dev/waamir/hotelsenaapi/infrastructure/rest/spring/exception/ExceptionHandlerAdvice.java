package dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApi(ApiException e) {
        return ResponseEntity
            .status(e.getStatus())
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message(e.getMessage())
                    .status(e.getStatus())
                    .build()
            );
    }
}

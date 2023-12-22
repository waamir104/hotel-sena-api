package dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.adapter.dto.ErrorField;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(
        MethodArgumentNotValidException e
    ) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ErrorField> errorFields = new ArrayList<ErrorField>();
        fieldErrors.forEach(fieldError -> {
            errorFields.add(
                ErrorField.builder()
                .code(fieldError.getCode())
                .fieldName(fieldError.getField())
                .rejectedValue(fieldError.getRejectedValue())
                .build()
            );
        });
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Invalid request fields")
                    .status(HttpStatus.BAD_REQUEST)
                    .data(
                        errorFields
                    )
                    .build()
            );
    }
}

package dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private HttpStatusCode status;
    private String message;

    public ApiException(String message, HttpStatusCode status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}

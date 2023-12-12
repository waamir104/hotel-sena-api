package dev.waamir.hotelsenaapi.adapter.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private LocalDateTime timeStamp;
    private HttpStatusCode status;
    private String message;
    private String data;
}

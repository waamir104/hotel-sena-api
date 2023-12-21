package dev.waamir.hotelsenaapi.adapter.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
    private HttpStatusCode status;
    private String message;
    private Object data;
}

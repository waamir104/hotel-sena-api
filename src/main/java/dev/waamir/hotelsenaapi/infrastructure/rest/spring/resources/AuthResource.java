package dev.waamir.hotelsenaapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.adapter.dto.MessageResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthResetPwdRequest;
import dev.waamir.hotelsenaapi.application.service.resources.AuthResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {
    
    @Value("${application.front-end.host}")
    private String frontHost;

    @Autowired
    private AuthResourceService authResourceService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register (
        @RequestBody AuthRegisterRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.CREATED)
                    .message("User created.")
                    .data(authResourceService.register(request))
                    .build()
            );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate (
        @RequestBody AuthRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("User authenticated.")
                    .data(authResourceService.authenticate(request))
                    .build()
            );
    }

    @PutMapping("/verify/{type}/{userId}")
    public ResponseEntity<ApiResponse> verify (
        @PathVariable String type,
        @PathVariable String userId
    ) {
        String url = frontHost.concat("/verify/" + type + "/" + userId);
        MessageResponse msg = authResourceService.verify(type, userId, url);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message(msg.getMessage())
                    .build()
            );
    }

    @GetMapping("/resetPwdRequest/{username}")
    public ResponseEntity<ApiResponse> resetPwsRequest(
        @PathVariable @NotNull String username
    ) {
        MessageResponse msg = authResourceService.resetPwdRequest(username);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message(msg.getMessage())
                    .build()
            );
    }

    @PutMapping("/resetPwd")
    public ResponseEntity<ApiResponse> resetPwd(
        @RequestBody @Valid AuthResetPwdRequest request
    ) {
        MessageResponse msg = authResourceService.resetPwd(request);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message(msg.getMessage())
                    .build()
            );
    }
}

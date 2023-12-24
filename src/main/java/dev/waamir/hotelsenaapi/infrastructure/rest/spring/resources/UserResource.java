package dev.waamir.hotelsenaapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.user.UserRegisterRequest;
import dev.waamir.hotelsenaapi.application.service.resources.UserResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    @Autowired
    private UserResourceService userResourceService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> fetchById(
        @PathVariable @NotEmpty String id
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("User fetched.")
                    .data(
                        userResourceService.fetchById(id)
                    )
                    .build()
            );
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse> fetchByUsername(
        @PathVariable @NotEmpty String username
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("User fetched.")
                    .data(
                        userResourceService.fetchByUsername(username)
                    )
                    .build()
            );
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> list(
        @PageableDefault(size = 10) Pageable pagination
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Users fetched.")
                    .data(
                        userResourceService.list(pagination)
                    )
                    .build()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
        @RequestBody @Valid UserRegisterRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("User registered successfully.")
                    .data(
                        userResourceService.register(request)
                    )
                    .build()
            );
    }
}

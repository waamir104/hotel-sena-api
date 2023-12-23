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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.guest.GuestRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.guest.GuestUpdateRequest;
import dev.waamir.hotelsenaapi.application.service.resources.GuestResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/guest")
public class GuestResource {

    @Autowired
    private GuestResourceService guestResourceService;

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
                    .message("Guests fetched.")
                    .data(
                        guestResourceService.list(pagination)
                    )
                    .build()
            );
    }

    @GetMapping("/docNumber/{number}")
    public ResponseEntity<ApiResponse> fetchByDocNumber(
        @PathVariable @NotNull Long number
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Guest fetched.")
                    .data(
                        guestResourceService.fetchByDocNumber(number)
                    )
                    .build()
            );
    }

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
                    .message("Guest fetched.")
                    .data(
                        guestResourceService.fetchById(id)
                    )
                    .build()
            );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> fetchByEmail(
        @PathVariable @NotEmpty @Email String email
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Guest fetched.")
                    .data(
                        guestResourceService.fetchByEmail(email)
                    )
                    .build()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
        @RequestBody @Valid GuestRegisterRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Guest registered successfully.")
                    .data(
                        guestResourceService.register(request)
                    )
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update ( 
        @RequestBody @Valid GuestUpdateRequest request
    ) {
        guestResourceService.update(request);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Guest updated successfully.")
                    .build()
            );
    }
}
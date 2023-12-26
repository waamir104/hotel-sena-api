package dev.waamir.hotelsenaapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.application.service.resources.AccountVerificationResourceService;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/v1/accountVerification")
public class AccountVerificationResource {
    
    @Autowired
    private AccountVerificationResourceService accountVerificationResourceService;

    @GetMapping("/resend/{username}")
    public ResponseEntity<ApiResponse> resendEmail(
        @PathVariable @NotEmpty String username
    ) {
        accountVerificationResourceService.resendEmail(username);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Email resent successfully.")
                    .build()
            );
    }
}

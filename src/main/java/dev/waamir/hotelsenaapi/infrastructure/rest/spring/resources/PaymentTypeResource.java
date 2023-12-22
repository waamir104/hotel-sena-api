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
import dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType.PaymentTypeRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType.PaymentTypeUpdateRequest;
import dev.waamir.hotelsenaapi.application.service.resources.PaymentTypeResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/v1/paymentType")
public class PaymentTypeResource {
    
    @Autowired
    private PaymentTypeResourceService paymentTypeResourceService;

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
                    .message("Payment types fetched.")
                    .data(
                        paymentTypeResourceService.list(pagination)
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
                    .message("Payment type fetched.")
                    .data(
                        paymentTypeResourceService.fetchById(id)
                    )
                    .build()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
        @RequestBody @Valid PaymentTypeRegisterRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Payment type registered successfully.")
                    .data(
                        paymentTypeResourceService.register(request)
                    )
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(
        @RequestBody @Valid PaymentTypeUpdateRequest request
    ) {
        paymentTypeResourceService.update(request);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Payment type updated successfully.")
                    .build()
            );
    }
}

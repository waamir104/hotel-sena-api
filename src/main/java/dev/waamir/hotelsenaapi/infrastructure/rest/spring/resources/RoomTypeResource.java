package dev.waamir.hotelsenaapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.roomType.RoomTypeRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.roomType.RoomTypeUpdateRequest;
import dev.waamir.hotelsenaapi.application.service.resources.RoomTypeResourceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roomType")
public class RoomTypeResource {
    
    @Autowired
    private RoomTypeResourceService roomTypeResourceService;

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
                    .message("Room types fetched")
                    .data(roomTypeResourceService.list(pagination))
                    .build()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
        @RequestBody @Valid RoomTypeRegisterRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())  
                    .status(HttpStatus.OK)
                    .message("Room type registered successfully.")
                    .data(roomTypeResourceService.register(request))
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(
        @RequestBody @Valid RoomTypeUpdateRequest request
    ) {
        roomTypeResourceService.update(request);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())  
                    .status(HttpStatus.OK)
                    .message("Room type updated successfully.")
                    .build()
            );
    }
}

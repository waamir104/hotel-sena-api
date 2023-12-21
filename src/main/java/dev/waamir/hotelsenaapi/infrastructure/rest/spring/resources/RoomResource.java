package dev.waamir.hotelsenaapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelsenaapi.adapter.dto.ApiResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.room.RoomRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.room.RoomUpdateRequest;
import dev.waamir.hotelsenaapi.application.service.resources.RoomResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/room")
public class RoomResource {
    
    @Autowired
    private RoomResourceService roomResourceService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> list(
        @PageableDefault(size = 10) Pageable pagination
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .status(HttpStatus.OK)
                    .timeStamp(LocalDateTime.now())
                    .message("Rooms fetched")
                    .data(
                        roomResourceService.list(pagination)
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
                    .message("Room fetched")
                    .data(
                        roomResourceService.fetchById(id)
                    )
                    .build()
            );
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<ApiResponse> fetchById(
        @PathVariable @NotNull Long number
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Room fetched")
                    .data(
                        roomResourceService.fetchByNumber(number)
                    )
                    .build()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
        @RequestBody @Valid RoomRegisterRequest request
    ) {
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Room registered successfully")
                    .data(
                        roomResourceService.register(request)
                    )
                    .build()
            );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(
        @RequestBody @Valid RoomUpdateRequest request
    ) {
        roomResourceService.update(request);
        return ResponseEntity
            .ok()
            .body(
                ApiResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .status(HttpStatus.OK)
                    .message("Room updateded successfully")
                    .build()
            );
    }
}

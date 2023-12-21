package dev.waamir.hotelsenaapi.adapter.dto.resources.roomType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomTypeRegisterRequest(
    @NotEmpty
    String name,
    @NotNull
    Double dailyPrice,
    @NotEmpty
    String description
) {
    
}

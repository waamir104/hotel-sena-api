package dev.waamir.hotelsenaapi.adapter.dto.resources.roomType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomTypeUpdateRequest(
    @NotEmpty
    String id,
    @NotEmpty
    String name,
    @NotNull
    Double dailyPrice,
    @NotEmpty 
    String description
) {
    
}

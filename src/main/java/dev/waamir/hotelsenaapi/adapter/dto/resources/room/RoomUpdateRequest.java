package dev.waamir.hotelsenaapi.adapter.dto.resources.room;

import jakarta.validation.constraints.NotEmpty;

public record RoomUpdateRequest(
    @NotEmpty
    String id,
    @NotEmpty
    String description
) {
    
}

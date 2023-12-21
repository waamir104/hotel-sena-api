package dev.waamir.hotelsenaapi.adapter.dto.resources.room;

import dev.waamir.hotelsenaapi.adapter.dto.resources.roomType.RoomTypeResponse;
import dev.waamir.hotelsenaapi.domain.model.Room;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomResponse (
    @NotEmpty
    String id,
    @NotNull
    Long number,
    @NotEmpty
    String description,
    @NotNull
    RoomTypeResponse type
) {
    
    public RoomResponse (Room room) {
        this(
            room.getId().toString(), 
            room.getNumber(),
            room.getDescription(),
            new RoomTypeResponse(room.getRoomType())
        );
    }
}

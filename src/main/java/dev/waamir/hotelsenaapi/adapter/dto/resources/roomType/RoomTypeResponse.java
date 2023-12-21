package dev.waamir.hotelsenaapi.adapter.dto.resources.roomType;

import dev.waamir.hotelsenaapi.domain.model.RoomType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomTypeResponse (
    @NotEmpty
    String id,
    @NotEmpty
    String name,
    @NotNull
    Double dailyPrice,
    @NotEmpty
    String description
) {

    public RoomTypeResponse(RoomType roomType) {
        this(
            roomType.getId().toString(),
            roomType.getName(),
            roomType.getDailyPrice(),
            roomType.getDescription()
        );
    }
}
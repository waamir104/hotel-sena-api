package dev.waamir.hotelsenaapi.adapter.dto.resources.room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomRegisterRequest(
    @NotNull
    Long number,
    @NotEmpty
    String description,
    @NotEmpty
    String roomTypeId
) {
}

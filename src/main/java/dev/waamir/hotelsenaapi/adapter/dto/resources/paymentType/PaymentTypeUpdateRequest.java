package dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType;

import jakarta.validation.constraints.NotEmpty;

public record PaymentTypeUpdateRequest(
    @NotEmpty
    String id,
    @NotEmpty
    String name
) {
    
}

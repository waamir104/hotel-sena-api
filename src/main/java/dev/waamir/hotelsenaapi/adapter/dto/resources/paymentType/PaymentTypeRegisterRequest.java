package dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType;

import jakarta.validation.constraints.NotEmpty;

public record PaymentTypeRegisterRequest(
    @NotEmpty
    String name
) {
    
}

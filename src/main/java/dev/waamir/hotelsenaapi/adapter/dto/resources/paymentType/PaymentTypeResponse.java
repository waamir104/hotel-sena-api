package dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType;

import dev.waamir.hotelsenaapi.domain.model.PaymentType;
import jakarta.validation.constraints.NotEmpty;

public record PaymentTypeResponse(
    @NotEmpty
    String id,
    @NotEmpty
    String name
) {
    
    public PaymentTypeResponse(PaymentType paymentType) {
        this(
            paymentType.getId().toString(),
            paymentType.getName()
        );
    }
}

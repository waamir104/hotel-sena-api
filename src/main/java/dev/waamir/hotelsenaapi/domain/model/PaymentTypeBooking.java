package dev.waamir.hotelsenaapi.domain.model;

import dev.waamir.hotelsenaapi.domain.enumeration.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTypeBooking {
    
    private PaymentType paymentType;
    private Integer percentage;
}

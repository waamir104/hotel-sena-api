package dev.waamir.hotelsenaapi.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {

    @NotNull
    @Pattern(regexp = "^3\\d{9,}$", message = "Invalid phone number.")
    private String phoneNumber;
}

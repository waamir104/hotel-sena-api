package dev.waamir.hotelsenaapi.adapter.dto.resources.guest;

import java.time.LocalDate;
import java.util.List;

import dev.waamir.hotelsenaapi.domain.enumeration.DocType;
import dev.waamir.hotelsenaapi.domain.model.Address;
import dev.waamir.hotelsenaapi.domain.model.PhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record GuestRegisterRequest(
    @NotNull
    Long docNumber,
    @NotNull
    DocType docType,
    @NotEmpty
    String name,
    @NotEmpty
    String lastName,
    @NotNull
    LocalDate birthDate,
    @NotEmpty
    String email,
    @Valid
    Address address,
    @NotNull
    List<PhoneNumber> phoneNumbers
) {
    
}

package dev.waamir.hotelsenaapi.adapter.dto.resources.guest;

import java.time.LocalDate;
import java.util.List;

import dev.waamir.hotelsenaapi.domain.enumeration.DocType;
import dev.waamir.hotelsenaapi.domain.model.Address;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.model.PhoneNumber;
import jakarta.validation.constraints.NotEmpty;

public record GuestResponse (
    @NotEmpty
    String id,
    Long docNumber,
    DocType docType,
    String name,
    String lastName,
    LocalDate birthDate,
    @NotEmpty
    String email,
    Address address,
    List<PhoneNumber> phoneNumbers
) {
    
    public GuestResponse (Guest guest) {
        this(
            guest.getId().toString(),
            guest.getDocNumber(),
            guest.getDocType(),
            guest.getName(),
            guest.getLastName(),
            guest.getBirthDate(),
            guest.getEmail(),
            guest.getAddress(),
            guest.getPhoneNumbers()
        );
    }
}

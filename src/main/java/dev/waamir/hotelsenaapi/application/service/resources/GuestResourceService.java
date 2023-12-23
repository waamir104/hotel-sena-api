package dev.waamir.hotelsenaapi.application.service.resources;

import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.resources.guest.GuestRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.guest.GuestResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.guest.GuestUpdateRequest;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IGuestRepository;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestResourceService {
    
    private final IGuestRepository<Guest> guestRepository;
    private final IUserRepository<User> userRepository;

    public Page<GuestResponse> list(Pageable pagination) {
        return guestRepository.list(pagination).map(GuestResponse::new);
    }

    public GuestResponse fetchByDocNumber(Long number) {
        Guest guest = guestRepository.getByDocNumber(number).orElseThrow(() -> {
            throw new ApiException("Guest not found.", HttpStatus.NOT_FOUND);
        });
        return new GuestResponse(guest);
    }

    public GuestResponse fetchById(String id) {
        Guest guest = guestRepository.getById(new ObjectId(id)).orElseThrow(() -> {
            throw new ApiException("Guest not found.", HttpStatus.NOT_FOUND);
        });
        return new GuestResponse(guest);
    }

    public GuestResponse fetchByEmail(String email) {
        Guest guest = guestRepository.getByEmail(email).orElseThrow(() -> {
            throw new ApiException("Guest not found.", HttpStatus.NOT_FOUND);
        });
        return new GuestResponse(guest);
    }

    public GuestResponse register(GuestRegisterRequest request) {
        Guest guestfetched = guestRepository.getByDocNumber(request.docNumber()).orElse(null);
        if (!Objects.isNull(guestfetched) && (guestRepository.countByEmail(request.email()) != 0)) throw new ApiException("Guest already registered.", HttpStatus.CONFLICT);
        Guest guest = Guest.builder()
            .name(request.name())
            .lastName(request.lastName())
            .docNumber(request.docNumber())
            .docType(request.docType())
            .email(request.email())
            .birthDate(request.birthDate())
            .address(request.address())
            .phoneNumbers(request.phoneNumbers())
            .build();
        guest = guestRepository.create(guest);
        return new GuestResponse(guest);
    }

    public void update(GuestUpdateRequest request) {
        Guest guest = guestRepository.getById(new ObjectId(request.id())).orElseThrow(() -> {
            throw new ApiException("Guest not found.", HttpStatus.NOT_FOUND);
        });
        User user = userRepository.getByUsername(guest.getEmail()).orElse(null);
        if (!Objects.isNull(user)) {
            user.setUsername(request.email());
            userRepository.update(user);
        }
        guest.setDocNumber(request.docNumber());
        guest.setDocType(request.docType());
        guest.setEmail(request.email());
        guest.setName(request.name());
        guest.setLastName(request.lastName());
        guest.setBirthDate(request.birthDate());
        guest.setAddress(request.address());
        guest.setPhoneNumbers(request.phoneNumbers());
        guestRepository.update(guest);
    }

}

package dev.waamir.hotelsenaapi.application.service.resources;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.resources.roomType.RoomTypeRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.roomType.RoomTypeResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.roomType.RoomTypeUpdateRequest;
import dev.waamir.hotelsenaapi.domain.model.RoomType;
import dev.waamir.hotelsenaapi.domain.port.IRoomTypeRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomTypeResourceService {
    
    private final IRoomTypeRepository<RoomType> roomTypeRepository;

    public Page<RoomTypeResponse> list(Pageable pagination) {
        return roomTypeRepository.list(pagination).map(RoomTypeResponse::new);
    }

    public RoomTypeResponse register(RoomTypeRegisterRequest request) {
        RoomType roomType = RoomType.builder()
            .dailyPrice(request.dailyPrice())
            .name(request.name())
            .description(request.description())
            .build();
        roomType = roomTypeRepository.create(roomType);
        return new RoomTypeResponse(roomType);
    }

    public void update(RoomTypeUpdateRequest request) {
        RoomType roomType = roomTypeRepository.getById(new ObjectId(request.id())).orElseThrow(
            () -> {
                throw new ApiException("Room type not found.", HttpStatus.NOT_FOUND);
            }
        );
        roomType.setName(request.name());
        roomType.setDailyPrice(request.dailyPrice());
        roomType.setDescription(request.description());
        roomTypeRepository.update(roomType);
    }
}

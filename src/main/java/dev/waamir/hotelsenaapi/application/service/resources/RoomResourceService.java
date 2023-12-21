package dev.waamir.hotelsenaapi.application.service.resources;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.resources.room.RoomRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.room.RoomResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.room.RoomUpdateRequest;
import dev.waamir.hotelsenaapi.domain.model.Room;
import dev.waamir.hotelsenaapi.domain.model.RoomType;
import dev.waamir.hotelsenaapi.domain.port.IRoomRepository;
import dev.waamir.hotelsenaapi.domain.port.IRoomTypeRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomResourceService {
    
    private final IRoomRepository<Room> roomRepository;
    private final IRoomTypeRepository<RoomType> roomTypeRepository;

    public Page<RoomResponse> list(Pageable pagination) {
        return roomRepository.list(pagination).map(RoomResponse::new);
    }

    public RoomResponse fetchById(String id) {
        Room room = roomRepository.getById(new ObjectId(id)).orElseThrow(() -> {
            throw new ApiException("Room not found.", HttpStatus.NOT_FOUND);
        });
        return new RoomResponse(room);
    }

    public RoomResponse fetchByNumber(Long number) {
        Room room = roomRepository.getByNumber(number).orElseThrow(() -> {
            throw new ApiException("Room not found.", HttpStatus.NOT_FOUND);
        });
        return new RoomResponse(room);
    }

    public RoomResponse register(RoomRegisterRequest request) {
        if (roomRepository.getByNumber(request.number()).isPresent()) {
            throw new ApiException("Room already registered.", HttpStatus.CONFLICT);
        }
        RoomType roomType = roomTypeRepository.getById(
            new ObjectId(request.roomTypeId())
        ).orElseThrow(() -> {
            throw new ApiException("Room type not found.", HttpStatus.NOT_FOUND);
        });
        Room room = Room.builder()
            .number(request.number())
            .description(request.description())
            .roomType(roomType)
            .build();
        room = roomRepository.create(room);
        return new RoomResponse(room);
    }

    public void update(RoomUpdateRequest request) {
        Room room = roomRepository.getById(
            new ObjectId(request.id())
        ).orElseThrow(() -> {
            throw new ApiException("Room not found.", HttpStatus.NOT_FOUND);
        }); 
        room.setDescription(request.description());
        roomRepository.update(room);
    }
}

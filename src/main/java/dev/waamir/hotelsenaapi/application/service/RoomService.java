package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IRoomMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Room;
import dev.waamir.hotelsenaapi.domain.port.IRoomRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomRepository<Room> {
    
    private final IRoomMongoRepository roomMongoRepository;

    @Override
    public Room create(Room room) {
        return roomMongoRepository.insert(room);
    }

    @Override
    public Optional<Room> getById(String id) {
        return roomMongoRepository.findById(id);
    }

    @Override
    public Optional<Room> getByNumber(Long number) {
        return roomMongoRepository.findByNumber(number);
    }

    @Override
    public void delete(Room room) {
        roomMongoRepository.delete(room);
    }

    @Override
    public void update(Room room) {
        roomMongoRepository.save(room);
    }

    @Override
    public Page<Room> list(Pageable pagination) {
        return roomMongoRepository.findAll(pagination);
    }

    @Override
    public List<Room> list() {
        return roomMongoRepository.findAll();
    }
    
}

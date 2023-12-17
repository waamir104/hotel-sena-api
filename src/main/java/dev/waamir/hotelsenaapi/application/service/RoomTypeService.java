package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IRoomTypeMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.RoomType;
import dev.waamir.hotelsenaapi.domain.port.IRoomTypeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RoomTypeService implements IRoomTypeRepository<RoomType> {
    
    private final IRoomTypeMongoRepository roomTypeMongoRepository;
    
    @Override
    public RoomType create(RoomType roomType) {
        return roomTypeMongoRepository.insert(roomType);
    }

    @Override
    public Optional<RoomType> getById(ObjectId id) {
        return roomTypeMongoRepository.findById(id);
    }

    @Override
    public Optional<RoomType> getByName(String name) {
        return roomTypeMongoRepository.findByName(name);
    }

    @Override
    public Long countByName(String name) {
        return roomTypeMongoRepository.countByName(name);
    }

    @Override
    public void delete(RoomType roomType) {
        roomTypeMongoRepository.delete(roomType);
    }

    @Override
    public void update(RoomType roomType) {
        roomTypeMongoRepository.save(roomType);
    }

    @Override
    public List<RoomType> list() {
        return roomTypeMongoRepository.findAll();
    }

    @Override
    public Page<RoomType> list(Pageable pagination) {
        return roomTypeMongoRepository.findAll(pagination);
    }
    
}

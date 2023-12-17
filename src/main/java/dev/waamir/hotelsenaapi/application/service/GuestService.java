package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IGuestMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.port.IGuestRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class GuestService implements IGuestRepository<Guest> {

    private final IGuestMongoRepository guestMongoRepository;

    @Override
    public Guest create(Guest guest) {
        return guestMongoRepository.insert(guest);
    }

    @Override
    public Optional<Guest> getById(ObjectId id) {
        return guestMongoRepository.findById(id);
    }

    @Override
    public Optional<Guest> getByEmail(String email) {
        return guestMongoRepository.findByEmail(email);
    }

    @Override
    public Optional<Guest> getByDocNumber(Long docNumber) {
        return guestMongoRepository.findByDocNumber(docNumber);
    }

    @Override
    public long countByEmail(String email) {
        return guestMongoRepository.countByEmail(email);
    }

    @Override
    public void delete(Guest guest) {
        guestMongoRepository.delete(guest);
    }

    @Override
    public void update(Guest guest) {
        guestMongoRepository.save(guest);
    }

    @Override
    public Page<Guest> list(Pageable pagination) {
        return guestMongoRepository.findAll(pagination);
    }

    @Override
    public List<Guest> list() {
        return guestMongoRepository.findAll();
    }
    
}

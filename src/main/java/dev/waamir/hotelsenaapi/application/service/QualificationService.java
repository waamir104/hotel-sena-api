package dev.waamir.hotelsenaapi.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IQualificationMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.model.Qualification;
import dev.waamir.hotelsenaapi.domain.port.IQualificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QualificationService implements IQualificationRepository<Qualification> {
    
    private final IQualificationMongoRepository qualificationMongoRepository;

    @Override
    public Qualification create(Qualification qualification) {
        return qualificationMongoRepository.insert(qualification);
    }

    @Override
    public Optional<Qualification> getById(String id) {
        return qualificationMongoRepository.findById(id);
    }

    @Override
    public void delete(Qualification qualification) {
        qualificationMongoRepository.delete(qualification);
    }

    @Override
    public void update(Qualification qualification) {
        qualificationMongoRepository.save(qualification);
    }

    @Override
    public Page<Qualification> list(Pageable pagination) {
        return qualificationMongoRepository.findAll(pagination);
    }

    @Override
    public Page<Qualification> listByGuest(Guest guest, Pageable pagination) {
        return qualificationMongoRepository.findByGuest(guest, pagination);
    }

    @Override
    public double getAverageRating() {
        return qualificationMongoRepository.fetchRatingAverage();
    }
    
}

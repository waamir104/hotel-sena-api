package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.model.Worker;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkerMongoRepository extends MongoRepository<Worker,String> {
    Optional<Worker> findByDocNumber(Long docNumber);
    Optional<Worker> findByEmail(String email);
    long countByEmail(String email);
}

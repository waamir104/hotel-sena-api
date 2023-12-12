package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.model.Guest;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IGuestMongoRepository extends MongoRepository<Guest,String> {

    Optional<Guest> findByEmail(String email);
    @Query("{ 'doc_number' : ?0 }")
    Optional<Guest> findByDocNumber(Long docNumber);
    @Query("{ 'email' : ?0 }")
    long countByEmail(String email);
}   

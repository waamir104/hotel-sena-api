package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.model.Guest;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IGuestMongoRepository extends MongoRepository<Guest, ObjectId> {

    Optional<Guest> findByEmail(String email);
    @Query("{ 'doc_number' : ?0 }")
    Optional<Guest> findByDocNumber(Long docNumber);
    @Query(value = "{ 'email' : ?0 }", count = true)
    long countByEmail(String email);
}   

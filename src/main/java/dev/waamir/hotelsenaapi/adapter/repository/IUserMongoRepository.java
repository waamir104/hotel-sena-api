package dev.waamir.hotelsenaapi.adapter.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.User;

@Repository
public interface IUserMongoRepository extends MongoRepository<User, ObjectId>{

    @Query(value = "{ 'username': ?0 }", count = true)
    Integer countByUsername(String username);

    @Query("{ 'username': ?0 }")
    Optional<User> findByUsername(String username);
}

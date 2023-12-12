package dev.waamir.hotelsenaapi.adapter.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.Role;

@Repository
public interface IRoleMongoRepository extends MongoRepository<Role, String> {
    
    @Query("{ 'name' : ?0 }")
    Optional<Role> findByName(String name);
}

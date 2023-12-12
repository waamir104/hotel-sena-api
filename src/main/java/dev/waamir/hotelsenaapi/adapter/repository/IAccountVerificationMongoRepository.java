package dev.waamir.hotelsenaapi.adapter.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.AccountVerification;

@Repository
public interface IAccountVerificationMongoRepository extends MongoRepository<AccountVerification, String> {
    
    @Query("{ 'user_id' : ?0 }")
    Optional<AccountVerification> findByUserId(String userId);

    @Query(value = "{ 'url' : ?0 }", count = true)
    Integer countByUrl(String url);

}

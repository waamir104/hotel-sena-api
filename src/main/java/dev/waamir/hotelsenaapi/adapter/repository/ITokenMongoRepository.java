package dev.waamir.hotelsenaapi.adapter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.Token;

@Repository
public interface ITokenMongoRepository extends MongoRepository<Token, String> {
    
    @Query("{ 'user_id' : ?0, 'revoked' : false }")
    List<Token> findAllValidTokensByUserId(String userId);

    @Query("{ 'token' : ?0 }")
    Optional<Token> findByToken(String token);
}

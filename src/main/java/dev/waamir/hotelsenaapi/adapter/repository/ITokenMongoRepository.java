package dev.waamir.hotelsenaapi.adapter.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.Token;
import dev.waamir.hotelsenaapi.domain.model.User;

@Repository
public interface ITokenMongoRepository extends MongoRepository<Token, ObjectId> {
    
    @Query("{ 'user_id' : ?0, 'revoked' : false }")
    List<Token> findAllValidTokensByUser(User user);

    @Query("{ 'token' : ?0 }")
    Optional<Token> findByToken(String token);
}

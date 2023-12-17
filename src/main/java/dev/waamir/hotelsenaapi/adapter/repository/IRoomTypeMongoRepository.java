package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.model.RoomType;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IRoomTypeMongoRepository extends MongoRepository<RoomType, ObjectId> {

    Optional<RoomType> findByName(String name);
    long countByName(String name);
}

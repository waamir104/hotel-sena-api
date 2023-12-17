package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.model.Room;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface IRoomMongoRepository extends MongoRepository<Room, ObjectId> {

    Optional<Room> findByNumber(Long number);
}

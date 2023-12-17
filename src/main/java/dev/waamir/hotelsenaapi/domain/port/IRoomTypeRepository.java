package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.RoomType;

public interface IRoomTypeRepository<T extends RoomType> {

    T create(T roomType);
    Optional<T> getById(ObjectId id);
    Optional<T> getByName(String name);
    Long countByName(String name);
    void delete(T roomType);
    void update(T roomType);
    List<T> list();
    Page<T> list(Pageable pagination);
    
}

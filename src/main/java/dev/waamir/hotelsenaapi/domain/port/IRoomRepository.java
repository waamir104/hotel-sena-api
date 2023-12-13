package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Room;

public interface IRoomRepository<T extends Room> {
    T create(T room);
    Optional<T> getById(String id);
    Optional<T> getByNumber(Long number);
    void delete(T room);
    void update(T room);
    Page<T> list(Pageable pagination);
    List<T> list();
}

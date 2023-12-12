package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Guest;

public interface IGuestRepository<T extends Guest> {
    T create(T guest);
    Optional<T> getById(String id);
    Optional<T> getByEmail(String email);
    Optional<T> getByDocNumber(Long docNumber);
    long countByEmail(String email);
    void delete(T guest);
    void update(T guest);
    Page<T> list(Pageable pagination);
    List<T> list();
}

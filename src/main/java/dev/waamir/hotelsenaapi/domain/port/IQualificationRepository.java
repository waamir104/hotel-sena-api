package dev.waamir.hotelsenaapi.domain.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.model.Qualification;

public interface IQualificationRepository<T extends Qualification> {
    T create(T qualification);
    Optional<T> getById(String id);
    void delete(T qualification);
    void update(T qualification);
    Page<T> list(Pageable pagination);
    Page<T> listByGuest(Guest guest, Pageable pagination);
    double getAverageRating();
}

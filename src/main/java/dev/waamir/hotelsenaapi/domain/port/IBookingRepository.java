package dev.waamir.hotelsenaapi.domain.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Booking;

public interface IBookingRepository<T extends Booking> {
    T create(T booking);
    Optional<T> getById(String id);
    Page<T> listByGuestEmail(String email, Pageable pagination);
    void delete(T booking);
    void update(T booking);
    Page<T> list(Pageable pagination);
    long countByBookingStatus(String bookingStatus);
}

package dev.waamir.hotelsenaapi.domain.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.enumeration.BookingStatus;
import dev.waamir.hotelsenaapi.domain.model.Booking;
import dev.waamir.hotelsenaapi.domain.model.Guest;

public interface IBookingRepository<T extends Booking> {
    T create(T booking);
    Optional<T> getById(String id);
    Page<T> listByGuestEmail(Guest guest, Pageable pagination);
    void delete(T booking);
    void update(T booking);
    Page<T> list(Pageable pagination);
    long countByBookingStatus(BookingStatus status);
}

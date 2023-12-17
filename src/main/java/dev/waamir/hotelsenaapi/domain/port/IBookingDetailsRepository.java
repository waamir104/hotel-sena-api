package dev.waamir.hotelsenaapi.domain.port;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Booking;
import dev.waamir.hotelsenaapi.domain.model.BookingDetails;

public interface IBookingDetailsRepository<T extends BookingDetails> {
    T create(T bookingDetails);
    Optional<T> getById(ObjectId id);
    Optional<T> getByBooking(Booking booking);
    void delete(T bookingbookingDetails);
    void update(T bookingbookingDetails);
    Page<T> list(Pageable pagination);
}

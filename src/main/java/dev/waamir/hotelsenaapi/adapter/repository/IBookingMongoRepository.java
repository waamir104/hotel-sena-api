package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.enumeration.BookingStatus;
import dev.waamir.hotelsenaapi.domain.model.Booking;
import dev.waamir.hotelsenaapi.domain.model.Guest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingMongoRepository extends MongoRepository<Booking, String> {

    Page<Booking> findByGuest(Guest guest, Pageable pagination);

    @Query("{ 'booking_status' : ?0 }")
    long countByBookingStatus(BookingStatus bookingStatus);
}

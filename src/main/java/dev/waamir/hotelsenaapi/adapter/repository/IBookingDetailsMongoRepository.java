package dev.waamir.hotelsenaapi.adapter.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.BookingDetails;
import dev.waamir.hotelsenaapi.domain.model.Booking;


@Repository
public interface IBookingDetailsMongoRepository extends MongoRepository<BookingDetails, ObjectId> {
    
    Optional<BookingDetails> findByBooking(Booking booking);
}

package dev.waamir.hotelsenaapi.application.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IBookingDetailsMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Booking;
import dev.waamir.hotelsenaapi.domain.model.BookingDetails;
import dev.waamir.hotelsenaapi.domain.port.IBookingDetailsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class BookingDetailsService implements IBookingDetailsRepository<BookingDetails> {
    
    private final IBookingDetailsMongoRepository bookingDetailsMongoRepository;

    @Override
    public BookingDetails create(BookingDetails bookingDetails) {
        return bookingDetailsMongoRepository.insert(bookingDetails);
    }

    @Override
    public Optional<BookingDetails> getById(ObjectId id) {
        return bookingDetailsMongoRepository.findById(id);
    }

    @Override
    public Optional<BookingDetails> getByBooking(Booking booking) {
        return bookingDetailsMongoRepository.findByBooking(booking);
    }

    @Override
    public void delete(BookingDetails bookingbookingDetails) {
        bookingDetailsMongoRepository.delete(bookingbookingDetails);
    }

    @Override
    public void update(BookingDetails bookingbookingDetails) {
        bookingDetailsMongoRepository.save(bookingbookingDetails);
    }

    @Override
    public Page<BookingDetails> list(Pageable pagination) {
        return bookingDetailsMongoRepository.findAll(pagination);
    }

}

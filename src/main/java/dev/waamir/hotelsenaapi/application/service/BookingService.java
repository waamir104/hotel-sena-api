package dev.waamir.hotelsenaapi.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IBookingMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Booking;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.port.IBookingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingRepository<Booking> {
    
    private final IBookingMongoRepository bookingMongoRepository;

    @Override
    public Booking create(Booking booking) {
        return bookingMongoRepository.insert(booking);
    }

    @Override
    public Optional<Booking> getById(String id) {
        return bookingMongoRepository.findById(id);
    }

    @Override
    public Page<Booking> listByGuestEmail(Guest guest, Pageable pagination) {
        return bookingMongoRepository.findByGuest(guest, pagination);
    }

    @Override
    public void delete(Booking booking) {
        bookingMongoRepository.delete(booking);
    }

    @Override
    public void update(Booking booking) {
        bookingMongoRepository.save(booking);
    }

    @Override
    public Page<Booking> list(Pageable pagination) {
        return bookingMongoRepository.findAll(pagination);
    }

    @Override
    public long countByBookingStatus(String bookingStatus) {
        return bookingMongoRepository.countByBookingStatus(bookingStatus);
    }

}

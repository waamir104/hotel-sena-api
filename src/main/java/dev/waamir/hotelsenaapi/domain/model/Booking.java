package dev.waamir.hotelsenaapi.domain.model;

import dev.waamir.hotelsenaapi.domain.enumeration.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "bookings")
public class Booking {
    
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @DBRef
    @Field(name = "guest_id")
    private Guest guest;
    @Field(name = "check_in")
    private LocalDate checkIn;
    @Field(name = "check_out")
    private LocalDate checkOut;
    @Field(name = "total_payment")
    private Double totalPayment;
    @Field(name = "status")
    private BookingStatus status;
    @Field(name = "payments")
    private List<PaymentTypeBooking> payments;
}

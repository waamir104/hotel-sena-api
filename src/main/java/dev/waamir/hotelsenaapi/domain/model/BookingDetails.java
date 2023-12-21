package dev.waamir.hotelsenaapi.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "booking_details")
public class BookingDetails {
    
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @DocumentReference(collection = "bookings", lazy = true, db = "hotel_sena_api")
    @Field(name = "booking_id")
    private Booking booking;
    @Field(name = "cant_people")
    private Long cantPeople;
    @Field(name = "observations")
    private String observations;
}

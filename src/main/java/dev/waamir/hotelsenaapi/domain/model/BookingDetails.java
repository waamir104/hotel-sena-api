package dev.waamir.hotelsenaapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class BookingDetails {
    
    @Id
    @Field(name = "_id")
    private String id;
    @DBRef
    @Field(name = "booking_id")
    private Booking booking;
    @Field(name = "cant_people")
    private Integer cantPeople;
    @Field(name = "observations")
    private String observations;
}

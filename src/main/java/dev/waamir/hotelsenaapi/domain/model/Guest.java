package dev.waamir.hotelsenaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Guests")
public class Guest {

    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "doc_guest")
    private Long docGuest;
    @Field(name = "doc_type")
    private String docType;
    @Field(name = "name")
    private String name;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "birth_date")
    private LocalDate birthDate;
    @Field(name = "email")
    private String email;
    @Field(name = "address")
    private Address address;
    @Field(name = "phone_number")
    private PhoneNumber[] phoneNumber;

}

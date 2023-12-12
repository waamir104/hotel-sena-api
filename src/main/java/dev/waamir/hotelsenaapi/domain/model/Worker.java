package dev.waamir.hotelsenaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import dev.waamir.hotelsenaapi.domain.enumeration.DocType;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("workers")
public class Worker {

    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "doc_worker")
    private Long docWorker;
    @Field(name = "doc_type")
    private DocType docType;
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
    @Field(name = "phone_numbers")
    private List<PhoneNumber> phoneNumbers;
}

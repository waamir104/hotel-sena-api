package dev.waamir.hotelsenaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "qualifications")
public class Qualification {
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @DBRef
    @Field(name = "guest_id")
    private Guest guest;
    @Field(name = "stars")
    private long stars;
    @Field(name = "description")
    private String description;
}

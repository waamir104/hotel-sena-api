package dev.waamir.hotelsenaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Qualifications")
public class Qualification {
    @Id
    @Field(name = "_id")
    private String id;
    @DBRef
    @Field(name = "guest_id")
    private String guest;
    @Field(name = "stars")
    private Integer stars;
    @Field(name = "description")
    private String description;
}
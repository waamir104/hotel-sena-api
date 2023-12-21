package dev.waamir.hotelsenaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "rooms")
public class Room {
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @Field(name = "number")
    private Long number;
    @Field(name = "description")
    private String description;
    @DocumentReference(collection = "room_types", lazy = true, db = "hotel_sena_api")
    @Field(name = "room_type_id")
    private RoomType roomType;
}

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
@Document(collection = "rooms")

public class Room {
    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "number")
    private Long number;
    @Field(name = "description")
    private String description;
    @DBRef
    @Field(name = "room_type_id")
    private RoomType roomType;
}

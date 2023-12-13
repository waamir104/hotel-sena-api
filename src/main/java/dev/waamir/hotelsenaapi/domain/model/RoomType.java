package dev.waamir.hotelsenaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "room_types")
public class RoomType {
    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "daily_price")
    private Double dailyPrice;
    @Field(name = "description")
    private String description;
}

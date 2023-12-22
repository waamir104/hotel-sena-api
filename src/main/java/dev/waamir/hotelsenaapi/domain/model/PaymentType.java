package dev.waamir.hotelsenaapi.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment_types")
public class PaymentType {
    
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @Field(name = "name")
    private String name;
}

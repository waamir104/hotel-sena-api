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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_verifications")
public class AccountVerification {
    
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @Field(name = "url")
    private String url;
    @DocumentReference(collection = "users", lazy = true, db = "hotel_sena_api")
    @Field(name = "user_id")
    private User user;
}

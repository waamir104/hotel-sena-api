package dev.waamir.hotelsenaapi.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import dev.waamir.hotelsenaapi.domain.enumeration.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tokens")
public class Token {
    
    @Id
    @Field(name = "_id")
    private ObjectId id;
    @Field(name = "token")
    private String token;
    @Field(name = "type")
    private TokenType type;
    @Field(name = "revoked")
    private boolean revoked;
    @DBRef
    @Field(name = "user_id")
    private User user;
}

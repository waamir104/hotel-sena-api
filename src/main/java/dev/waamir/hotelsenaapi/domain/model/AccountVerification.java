package dev.waamir.hotelsenaapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountVerification {
    
    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "url")
    private String url;
    @DBRef
    @Field(name = "user_id")
    private User user;
}

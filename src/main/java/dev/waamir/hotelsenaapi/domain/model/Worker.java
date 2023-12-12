package dev.waamir.hotelsenaapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("workers")
public class Worker {
    @Id
    @Field(name = "_id")
    private String id;
    private
}

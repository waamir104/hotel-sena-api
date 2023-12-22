package dev.waamir.hotelsenaapi.adapter.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelsenaapi.domain.model.PaymentType;

@Repository
public interface IPaymentTypeMongoRepository extends MongoRepository<PaymentType, ObjectId> {
}

package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.PaymentType;

public interface IPaymentTypeRepository<T extends PaymentType> {
    
    PaymentType create(T paymentType);
    Optional<PaymentType> getById(ObjectId id);
    List<T> list();
    Page<T> list(Pageable pagination);
    void update(T paymentType);
    void delete(T paymentType);

}

package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IPaymentTypeMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.PaymentType;
import dev.waamir.hotelsenaapi.domain.port.IPaymentTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTypeService implements IPaymentTypeRepository<PaymentType> {
    
    private final IPaymentTypeMongoRepository paymentTypeMongoRepository;

    @Override
    public PaymentType create(PaymentType paymentType) {
        return paymentTypeMongoRepository.insert(paymentType);
    }

    @Override
    public Optional<PaymentType> getById(ObjectId id) {
        return paymentTypeMongoRepository.findById(id);
    }

    @Override
    public List<PaymentType> list() {
        return paymentTypeMongoRepository.findAll();
    }

    @Override
    public Page<PaymentType> list(Pageable pagination) {
        return paymentTypeMongoRepository.findAll(pagination);
    }

    @Override
    public void update(PaymentType paymentType) {
        paymentTypeMongoRepository.save(paymentType);
    }

    @Override
    public void delete(PaymentType paymentType) {
        paymentTypeMongoRepository.delete(paymentType);
    }

}

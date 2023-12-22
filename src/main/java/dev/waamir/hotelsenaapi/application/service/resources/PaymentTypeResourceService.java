package dev.waamir.hotelsenaapi.application.service.resources;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType.PaymentTypeRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType.PaymentTypeResponse;
import dev.waamir.hotelsenaapi.adapter.dto.resources.paymentType.PaymentTypeUpdateRequest;
import dev.waamir.hotelsenaapi.domain.model.PaymentType;
import dev.waamir.hotelsenaapi.domain.port.IPaymentTypeRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTypeResourceService {
    
    private final IPaymentTypeRepository<PaymentType> paymentTypeRepository;

    public Page<PaymentTypeResponse> list(Pageable pagination) {
        return paymentTypeRepository.list(pagination).map(PaymentTypeResponse::new);
    }

    public PaymentTypeResponse fetchById(String id) {
        PaymentType paymentType = paymentTypeRepository.getById(
            new ObjectId(id)
        ).orElseThrow(() -> {
            throw new ApiException("Payment type not found.", HttpStatus.NOT_FOUND);
        });
        return new PaymentTypeResponse(paymentType);
    }

    public PaymentTypeResponse register(PaymentTypeRegisterRequest request) {
        PaymentType paymentType = PaymentType.builder()
            .name(request.name())
            .build();
        paymentType = paymentTypeRepository.create(paymentType);
        return new PaymentTypeResponse(paymentType);
    }

    public void update(PaymentTypeUpdateRequest request) {
        PaymentType paymentType = paymentTypeRepository.getById(
            new ObjectId(request.id())
        ).orElseThrow(() -> {
            throw new ApiException("Payment type not found.", HttpStatus.NOT_FOUND);
        });
        paymentType.setName(request.name());
        paymentTypeRepository.update(paymentType);
    }

}

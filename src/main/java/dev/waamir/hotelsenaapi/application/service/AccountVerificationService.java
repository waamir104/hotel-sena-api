package dev.waamir.hotelsenaapi.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IAccountVerificationMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.AccountVerification;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IAccountVerificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountVerificationService implements IAccountVerificationRepository<AccountVerification> {

    private final IAccountVerificationMongoRepository accountVerificationMongoRepository;

    @Override
    public AccountVerification create(AccountVerification accountVerification) {
        return accountVerificationMongoRepository.insert(accountVerification);
    }

    @Override
    public Optional<AccountVerification> getByUser(User user) {
        return accountVerificationMongoRepository.findByUserId(user.getId());
    }

    @Override
    public Integer getUrlCount(String url) {
        return accountVerificationMongoRepository.countByUrl(url);
    }
    
}

package dev.waamir.hotelsenaapi.domain.port;

import java.util.Optional;

import dev.waamir.hotelsenaapi.domain.model.AccountVerification;
import dev.waamir.hotelsenaapi.domain.model.User;

public interface IAccountVerificationRepository<T extends AccountVerification> {
    T create(T accountVerification);
    Optional<T> getByUser(User user);
    Integer getUrlCount(String url);
}

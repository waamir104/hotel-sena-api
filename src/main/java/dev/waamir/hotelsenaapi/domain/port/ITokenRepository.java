package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelsenaapi.domain.model.Token;
import dev.waamir.hotelsenaapi.domain.model.User;

public interface ITokenRepository<T extends Token> {
	
    T create(T token);
    void update(T token);
    void updateAll(List<T> tokens);
	List<T> getAllValidTokensByUser(User user);
	Optional<T> getByToken(String token);

}

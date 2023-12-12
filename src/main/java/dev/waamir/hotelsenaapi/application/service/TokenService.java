package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.ITokenMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Token;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.ITokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService implements ITokenRepository<Token> {
    
    private final ITokenMongoRepository tokenMongoRepository;

    @Override
    public Token create(Token token) {
        return tokenMongoRepository.insert(token);
    }

    @Override
    public void update(Token token) {
        tokenMongoRepository.save(token);
    }

    @Override
    public void updateAll(List<Token> tokens) {
        tokenMongoRepository.saveAll(tokens);
    }

    @Override
    public List<Token> getAllValidTokensByUser(User user) {
        return tokenMongoRepository.findAllValidTokensByUser(user);
    }

    @Override
    public Optional<Token> getByToken(String token) {
        return tokenMongoRepository.findByToken(token);
    }

}

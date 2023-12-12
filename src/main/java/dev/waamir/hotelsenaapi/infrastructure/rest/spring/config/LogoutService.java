package dev.waamir.hotelsenaapi.infrastructure.rest.spring.config;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.domain.model.Token;
import dev.waamir.hotelsenaapi.domain.port.ITokenRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    
    private final ITokenRepository<Token> tokenRepository;

    @Override
    public void logout(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.replace("Bearer ", "");
        Token storedToken = tokenRepository.getByToken(jwt).orElseThrow(
            () -> {
                throw new ApiException("Invalid token.", HttpStatus.BAD_REQUEST);
            }
        );
        if (!Objects.isNull(storedToken)) {
            storedToken.setRevoked(true);
            tokenRepository.update(storedToken);
        }
    }


}

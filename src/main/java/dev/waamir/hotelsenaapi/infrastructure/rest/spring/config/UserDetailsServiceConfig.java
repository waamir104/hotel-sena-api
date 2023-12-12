package dev.waamir.hotelsenaapi.infrastructure.rest.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {
    
    private final IUserRepository<User> userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.getByUsername(username)
            .orElseThrow(() -> {
                throw new ApiException("User not found.", HttpStatus.NOT_FOUND);
            });
    }
}

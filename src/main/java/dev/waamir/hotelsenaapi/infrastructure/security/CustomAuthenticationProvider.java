package dev.waamir.hotelsenaapi.infrastructure.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final IUserRepository<User> userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        final String password = authentication.getCredentials().toString();
        if (StringUtils.isEmpty(username)) {
            throw new ApiException("Bad credentials", HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = null;
        try {
            userDetails = userRepository.getByUsername(username)
                .orElseThrow(() -> {
                    throw new ApiException("User not found", HttpStatus.NOT_FOUND);
                });
        } catch (ApiException e) { 
            throw new ApiException("Bad credentials", HttpStatus.BAD_REQUEST);
        }
        if (!userDetails.isEnabled()) {
            throw new ApiException("User disabled verify your account or contact the admin.", HttpStatus.FORBIDDEN);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApiException("Bad credentials", HttpStatus.BAD_REQUEST);
        }
        return createSuccessfulAuthentication(authentication, userDetails);
    }

    private Authentication createSuccessfulAuthentication(Authentication auth, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), auth.getCredentials(), userDetails.getAuthorities());
        token.setDetails(auth.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
            .isAssignableFrom(authentication));
    }
    

}

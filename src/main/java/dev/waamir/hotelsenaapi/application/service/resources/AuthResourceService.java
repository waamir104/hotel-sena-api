package dev.waamir.hotelsenaapi.application.service.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static dev.waamir.hotelsenaapi.domain.enumeration.RoleName.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import dev.waamir.hotelsenaapi.adapter.dto.email.EmailDetails;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthResponse;
import dev.waamir.hotelsenaapi.domain.enumeration.TokenType;
import dev.waamir.hotelsenaapi.domain.model.AccountVerification;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.model.Role;
import dev.waamir.hotelsenaapi.domain.model.Token;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IAccountVerificationRepository;
import dev.waamir.hotelsenaapi.domain.port.IEmailService;
import dev.waamir.hotelsenaapi.domain.port.IGuestRepository;
import dev.waamir.hotelsenaapi.domain.port.IRoleRepository;
import dev.waamir.hotelsenaapi.domain.port.ITokenRepository;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import dev.waamir.hotelsenaapi.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthResourceService {
    
    @Value("${application.front-end.host}")
    private String frontHost;

    private final IRoleRepository<Role> roleRepository;
    private final IUserRepository<User> userRepository;
    private final ITokenRepository<Token> tokenRepository;
    private final IGuestRepository<Guest> guestRepository;
    private final IAccountVerificationRepository<AccountVerification> accountVerificationRepository;
    private final IEmailService emailService;
    private final Encoder encoder;
    private final Decoder decoder;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthResponse register(AuthRegisterRequest request) {
        if (!request.getPassword1().equals(request.getPassword2())) throw new ApiException("Passwords don't match.", HttpStatus.BAD_REQUEST);
        Role role = roleRepository.getByName(GUEST).orElseThrow(() -> {
            throw new ApiException("Role not found.", HttpStatus.NOT_FOUND);
        });
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword1()))
            .createdAt(LocalDateTime.now())
            .role(role)
            .enabled(false)
            .build();
        user = userRepository.create(user);
        String verificationUrl = getVerificationUrl(encode(user.getId()), "ACCOUNT");
        AccountVerification accountVerification = AccountVerification.builder()
            .url(verificationUrl)
            .user(user)
            .build();
        accountVerificationRepository.create(accountVerification);
        if (user.getRole().getName() == GUEST) {
            Guest guest = Guest.builder()
                .email(user.getUsername())
                .build();
            if (guestRepository.countByEmail(guest.getEmail()) == 0) {
                guestRepository.create(guest);
            }
        }
        EmailDetails emailDetails = EmailDetails.builder()
            .recipient(user.getUsername())
            .subject("Verify Account")
            .msgBody(emailService.getConfirmationMessage(verificationUrl))
            .build();
        emailService.sendEmail(emailDetails);
        String jwt = jwtService.generateJwt(user);
        saveUserToken(user, jwt);
        return AuthResponse.builder()
            .token(jwt)
            .role(user.getRole().getName())
            .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.getByUsername(request.getUsername()).orElseThrow(() -> {
            throw new ApiException("User not found.", HttpStatus.NOT_FOUND);
        });
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(), 
                user.getPassword()
            )
        );
        String jwt = jwtService.generateJwt(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwt);
        return AuthResponse.builder()
            .token(jwt)
            .role(user.getRole().getName())
            .build();
    }

    public void verify(String type, String userIdEncoded, String url) {
        if (!(accountVerificationRepository.getUrlCount(url) == Integer.valueOf(1))) throw new ApiException("An error ocurred validating the url.", HttpStatus.BAD_REQUEST);
        byte[] userIdDecodedArray = decoder.decode(userIdEncoded);
        String userIdString = new String(userIdDecodedArray, StandardCharsets.UTF_8);
        User user = userRepository.getById(userIdString).orElseThrow(() -> {
            throw new ApiException("User not found.", HttpStatus.BAD_REQUEST);
        });
        if (type.equals("ACCOUNT")) {
            if (user.isEnabled()) throw new ApiException("User already verified.", HttpStatus.OK);
            user.setEnabled(true);
            userRepository.update(user);
        }
    }

    private void saveUserToken(User user, String jwt) {
        Token token = Token.builder()
            .user(user)
            .token(jwt)
            .type(TokenType.BEARER)
            .revoked(false)
            .build();
        tokenRepository.create(token);
    }

    private String getVerificationUrl(String key, String type) {
        return frontHost.concat("/verify/" + type + "/" + key);
    }

    private String encode(String id) {
        byte[] idBytes = id.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(idBytes);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.getAllValidTokensByUser(user);
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
        });
        tokenRepository.updateAll(validUserTokens);
    }
}

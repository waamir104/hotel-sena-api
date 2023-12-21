package dev.waamir.hotelsenaapi.application.service.resources;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static dev.waamir.hotelsenaapi.domain.enumeration.RoleName.*;

import java.time.LocalDateTime;
import java.util.List;

import dev.waamir.hotelsenaapi.adapter.dto.MessageResponse;
import dev.waamir.hotelsenaapi.adapter.dto.email.EmailDetails;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthResetPwdRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.auth.AuthResponse;
import dev.waamir.hotelsenaapi.domain.enumeration.RoleName;
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
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthResponse register(AuthRegisterRequest request) {
        if (!request.getPassword1().equals(request.getPassword2())) throw new ApiException("Passwords don't match.", HttpStatus.BAD_REQUEST);
        Role role = roleRepository.getByName(GUEST).orElseThrow(() -> {
            throw new ApiException("Role not found.", HttpStatus.NOT_FOUND);
        });
        long usernameCount = userRepository.getUsernameCount(request.getUsername());
        if (usernameCount != 0) throw new ApiException("User already registered.", HttpStatus.BAD_REQUEST);
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword1()))
            .createdAt(LocalDateTime.now())
            .role(role)
            .enabled(false)
            .build();
        user = userRepository.create(user);
        String verificationUrl = getVerificationUrl(user.getId().toString(), "ACCOUNT");
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
                request.getPassword()
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

    public MessageResponse verify(String type, String userId, String url) {
        if (!(accountVerificationRepository.getUrlCount(url) == Integer.valueOf(1))) throw new ApiException("An error ocurred validating the url.", HttpStatus.BAD_REQUEST);
        User user = userRepository.getById(new ObjectId(userId)).orElseThrow(() -> {
            System.out.println(userId);
            throw new ApiException("User not found.", HttpStatus.BAD_REQUEST);
        });
        if (type.equals("ACCOUNT")) {
            if (user.isEnabled()) throw new ApiException("User already verified.", HttpStatus.OK);
            user.setEnabled(true);
            userRepository.update(user);
        }
        return MessageResponse.builder()
            .message("User verified.")
            .build();
    }

    public MessageResponse resetPwdRequest(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(() -> {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        });
        if (!user.getRole().getName().equals(RoleName.GUEST)) throw new ApiException("User cannot change the password. Please contact the admin.", HttpStatus.CONFLICT);
        if (!user.isEnabled()) throw new ApiException("User is disabled.", HttpStatus.CONFLICT);
        String jwt = jwtService.generateJwt(user);
        String resetUrl = frontHost.concat(String.format("/resetPassword?token=%s", jwt));
        EmailDetails emailDetails = EmailDetails.builder()
            .recipient(user.getUsername())
            .subject("Reset Password")
            .msgBody(emailService.getResetPwdMessage(resetUrl, user))
            .build();
        emailService.sendEmail(emailDetails);
        return MessageResponse.builder()
            .message("Reset password request successfully registered")
            .build();
    }

    public MessageResponse resetPwd(AuthResetPwdRequest request) {
        User user = userRepository.getByUsername(request.username()).orElseThrow(() -> {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        });
        if (!jwtService.isTokenValid(request.token(), user)) throw new ApiException("Token invalid", HttpStatus.BAD_REQUEST);
        if (!request.password1().equals(request.password2())) throw new ApiException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(request.password1()));
        userRepository.update(user);
        EmailDetails emailDetails = EmailDetails.builder()
            .recipient(user.getUsername())
            .subject("Reset Confirmation")
            .msgBody(emailService.getResetPwdConfirmationMessage())
            .build();
        emailService.sendEmail(emailDetails);
        return MessageResponse.builder()
            .message("Password reseted successfully")
            .build();
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

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.getAllValidTokensByUser(user);
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
        });
        tokenRepository.updateAll(validUserTokens);
    }
}

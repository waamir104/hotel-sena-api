package dev.waamir.hotelsenaapi.application.service.resources;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Base64.Encoder;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.email.EmailDetails;
import dev.waamir.hotelsenaapi.adapter.dto.resources.user.UserRegisterRequest;
import dev.waamir.hotelsenaapi.adapter.dto.resources.user.UserResponse;
import dev.waamir.hotelsenaapi.domain.enumeration.RoleName;
import dev.waamir.hotelsenaapi.domain.model.AccountVerification;
import dev.waamir.hotelsenaapi.domain.model.Role;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IAccountVerificationRepository;
import dev.waamir.hotelsenaapi.domain.port.IEmailService;
import dev.waamir.hotelsenaapi.domain.port.IRoleRepository;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserResourceService {

    @Value("${application.front-end.host}")
    private String frontHost;
    
    private final IUserRepository<User> userRepository;
    private final IRoleRepository<Role> roleRepository;
    private final IAccountVerificationRepository<AccountVerification> accountVerificationRepository;
    private final IEmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final Encoder encoder;

    public UserResponse fetchById(String id) {
        User user = userRepository.getById(new ObjectId(id)).orElseThrow(
            () -> {
                throw new ApiException("User not found", HttpStatus.NOT_FOUND);
            }
        );
        return new UserResponse(user);
    }

    public Page<UserResponse> list(Pageable pagination) {
        return userRepository.paginate(pagination).map(UserResponse::new);
    }

    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.getUsernameCount(request.username()) != 0) throw new ApiException("Username is unable.", HttpStatus.CONFLICT);
        if (!request.password().equals(request.pwdConfirmation())) throw new ApiException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        Role role = roleRepository.getById(new ObjectId(request.roleId())).orElseThrow(
            () -> {
                throw new ApiException("Role not found.", HttpStatus.NOT_FOUND);
            }
        );
        boolean enabled = !(role.getName() == RoleName.GUEST);
        User user = User.builder()
            .username(request.username())
            .password(passwordEncoder.encode(request.password()))
            .enabled(enabled)
            .role(role)
            .createdAt(LocalDateTime.now())
            .build();
        userRepository.create(user);
        if (user.getRole().getName() == RoleName.GUEST) {
            String verificationUrl = getVerificationUrl(
                            encoder.encodeToString(user.getId().toString().getBytes(StandardCharsets.UTF_8)), 
                            "ACCOUNT"
                            );
            EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getUsername())
                .subject("Verify Account")
                .msgBody(
                    emailService.getConfirmationMessage(verificationUrl)
                    )
                .build();
            emailService.sendEmail(emailDetails);
            AccountVerification accountVerification = AccountVerification.builder()
                .user(user)
                .url(verificationUrl)
                .build();
            accountVerificationRepository.create(accountVerification);
        }
        return new UserResponse(user);
    }

    public UserResponse fetchByUsername(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(
            () -> {
                throw new ApiException("User not found", HttpStatus.NOT_FOUND);
            }
        );
        return new UserResponse(user);
    }

    public void disable(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(
            () -> {
                throw new ApiException("User not found", HttpStatus.NOT_FOUND);
            }
        );
        user.setEnabled(false);
        userRepository.update(user);
        AccountVerification accountVerification = accountVerificationRepository.getByUser(user).orElse(null);
        if (!Objects.isNull(accountVerification)) {
            accountVerificationRepository.delete(accountVerification);
        }
    }

    public void enable(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(
            () -> {
                throw new ApiException("User not found", HttpStatus.NOT_FOUND);
            }
        );
        user.setEnabled(true);
        userRepository.update(user);
    }

    private String getVerificationUrl(String key, String type) {
        return frontHost.concat("/verify/" + type + "/" + key);
    }
}

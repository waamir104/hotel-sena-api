package dev.waamir.hotelsenaapi.application.service.resources;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.email.EmailDetails;
import dev.waamir.hotelsenaapi.domain.model.AccountVerification;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IAccountVerificationRepository;
import dev.waamir.hotelsenaapi.domain.port.IEmailService;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import dev.waamir.hotelsenaapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountVerificationResourceService {
    
    private final IAccountVerificationRepository<AccountVerification> accountVerificationRepository;
    private final IUserRepository<User> userRepository;
    private final IEmailService emailService;

    public void resendEmail(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(
            () -> {
                throw new ApiException("User not found.", HttpStatus.NOT_FOUND);
            }
        );
        AccountVerification accountVerification = accountVerificationRepository.getByUser(user).orElseThrow(
            () -> {
                throw new ApiException("Account verification not found.", HttpStatus.NOT_FOUND);
            }
        );
        EmailDetails emailDetails = EmailDetails.builder()
            .recipient(user.getUsername())
            .subject("Verify Account")
            .msgBody(
                emailService.getConfirmationMessage(accountVerification.getUrl())
            )
            .build();
        emailService.sendEmail(emailDetails);
    }
}

package dev.waamir.hotelsenaapi.domain.port;

import dev.waamir.hotelsenaapi.adapter.dto.email.EmailDetails;
import dev.waamir.hotelsenaapi.domain.model.Guest;
import dev.waamir.hotelsenaapi.domain.model.User;

public interface IEmailService {
    void sendEmail(EmailDetails emailDetails);
    String getConfirmationMessage(String url);
    String getRegistrationMessage(Guest guest, String url);
    String getResetPwdMessage(String url, User user);
    String getResetPwdConfirmationMessage();
}

package dev.waamir.hotelsenaapi.adapter.dto.resources.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthResetPwdRequest (
    @NotEmpty
    String username,
    @NotEmpty
    String password1,
    @NotEmpty
    String password2,
    @NotEmpty
    String token
) {
    
}

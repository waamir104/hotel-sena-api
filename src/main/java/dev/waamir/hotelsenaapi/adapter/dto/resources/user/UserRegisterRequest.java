package dev.waamir.hotelsenaapi.adapter.dto.resources.user;

import jakarta.validation.constraints.NotEmpty;

public record UserRegisterRequest(
    @NotEmpty
    String username,
    @NotEmpty
    String password,
    @NotEmpty
    String pwdConfirmation,
    @NotEmpty
    String roleId
) {
    
}

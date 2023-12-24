package dev.waamir.hotelsenaapi.adapter.dto.resources.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(
    @NotEmpty
    String username,
    @NotEmpty
    String password,
    @NotEmpty
    String pwdConfirmation,
    @NotNull
    Boolean enabled,
    @NotEmpty
    String roleId
) {
    
}

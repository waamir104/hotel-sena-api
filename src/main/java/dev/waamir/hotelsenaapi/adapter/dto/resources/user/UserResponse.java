package dev.waamir.hotelsenaapi.adapter.dto.resources.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.waamir.hotelsenaapi.adapter.dto.resources.role.RoleResponse;
import dev.waamir.hotelsenaapi.domain.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserResponse(
    @NotEmpty
    String id,
    @NotEmpty
    String username,
    @NotNull
    Boolean enabled,
    @NotNull
    RoleResponse role,
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt
) {
    
    public UserResponse(User user) {
        this(
            user.getId().toString(), 
            user.getUsername(), 
            user.isEnabled(), 
            new RoleResponse(user.getRole()), 
            user.getCreatedAt()
        );
    }
}

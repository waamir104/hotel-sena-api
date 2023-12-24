package dev.waamir.hotelsenaapi.adapter.dto.resources.role;

import dev.waamir.hotelsenaapi.domain.enumeration.RoleName;
import dev.waamir.hotelsenaapi.domain.model.Role;
import jakarta.validation.constraints.NotEmpty;

public record RoleResponse(
    @NotEmpty
    String id,
    @NotEmpty
    RoleName name
) {
    
    public RoleResponse (Role role) {
        this(role.getId().toString(), role.getName());
    }
}

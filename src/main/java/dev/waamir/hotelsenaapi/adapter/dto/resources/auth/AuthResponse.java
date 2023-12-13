package dev.waamir.hotelsenaapi.adapter.dto.resources.auth;

import dev.waamir.hotelsenaapi.domain.enumeration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private RoleName role;
}

package dev.waamir.hotelsenaapi.adapter.dto.resources.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    
    private String username;
    private String password;
    
}

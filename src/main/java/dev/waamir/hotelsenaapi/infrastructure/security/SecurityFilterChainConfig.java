package dev.waamir.hotelsenaapi.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import dev.waamir.hotelsenaapi.infrastructure.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainConfig {
    
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;

    // Fill with the resource's paths
    private static final String[] WHITE_LIST_URLS = {
        "/api/v1/auth/**", "/api/v1/room/list", "/api/v1/room/{id}",
        "/api/v1/room/number/{number}", "/api/v1/roomType/list"
    };
    private static final String[] GET_ADMIN_WORKER_PATHS = {
        "/api/v1/guest/list", "/api/v1/guest/docNumber/{number}", "/api/v1/guest/{id}",
        "/api/v1/guest/email/{email}"
    };
    private static final String[] PUT_ADMIN_WORKER_PATHS = {
        "/api/v1/guest/update"
    };
    private static final String[] POST_ADMIN_WORKER_PATHS = {
        "/api/v1/guest/register"
    };
    private static final String[] DELETE_ADMIN_WORKER_PATHS = {
    };
    private static final String[] POST_ADMIN_PATHS = {
        "/api/v1/room/register", "/api/v1/roomType/register", "/api/v1/paymentType/register"
    };
    private static final String[] PUT_ADMIN_PATHS = {
        "/api/v1/room/update", "/api/v1/roomType/update", "/api/v1/paymentType/update"
    };
    private static final String[] GET_ADMIN_PATHS = {
    };
    private static final String[] DELETE_ADMIN_PATHS = {
    };
    private static final String[] GET_ADMIN_WORKER_GUEST_PATHS = {
        "/api/v1/paymentType/list", "/api/v1/paymentType/{id}"
    };
    private static final String[] POST_ADMIN_WORKER_GUEST_PATHS = {
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(
            req -> req.requestMatchers(WHITE_LIST_URLS)
                .permitAll()
                .requestMatchers(HttpMethod.POST, POST_ADMIN_PATHS).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, PUT_ADMIN_PATHS).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, DELETE_ADMIN_PATHS).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, GET_ADMIN_PATHS).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, POST_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .requestMatchers(HttpMethod.PUT, PUT_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .requestMatchers(HttpMethod.GET, GET_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .requestMatchers(HttpMethod.DELETE, DELETE_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .requestMatchers(HttpMethod.GET, GET_ADMIN_WORKER_GUEST_PATHS).hasAnyAuthority("ADMIN", "WORKER", "GUEST")
                .requestMatchers(HttpMethod.POST, POST_ADMIN_WORKER_GUEST_PATHS).hasAnyAuthority("ADMIN", "WORKER", "GUEST")
                .anyRequest()
                .authenticated()
        );
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.logout(logout -> logout
            .logoutUrl("/api/v1/auth/logout")
            .addLogoutHandler(logoutHandler)
            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        );
        return http.build();
    }
}

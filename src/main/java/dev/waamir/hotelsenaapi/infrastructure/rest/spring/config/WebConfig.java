package dev.waamir.hotelsenaapi.infrastructure.rest.spring.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {
    
    @Value("${application.front-end.host}")
    private String frontHost;

    @Value("${cors.allowed.methods}")
    private List<String> methods;

    @Value("${cors.maxAge}")
    private Long maxAge;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedMethods(methods);
        corsConfig.setAllowedOrigins(Arrays.asList(frontHost));
        corsConfig.setAllowedHeaders(Arrays.asList(
            "Origin", "Access-Controll-Allow-Origin", "Content-Type", "Accept", "Jwt-Token",
            "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Accerss-Control-Request-Headers"
        ));
        corsConfig.setExposedHeaders(Arrays.asList(
            "Origin", "Access-Controll-Allow-Origin", "Content-Type", "Accept", "Jwt-Token",
            "Authorization", "Access-Control-Allow-Credentials", "File-Name"
        ));
        corsConfig.setMaxAge(maxAge);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Bean
    public CorsFilter corsWebFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}

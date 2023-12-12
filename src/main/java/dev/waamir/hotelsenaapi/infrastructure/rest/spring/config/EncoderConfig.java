package dev.waamir.hotelsenaapi.infrastructure.rest.spring.config;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncoderConfig {
    
    @Bean
    public Encoder base64Encoder() {
        return Base64.getEncoder();
    }
}

package dev.waamir.hotelsenaapi.infrastructure.rest.spring.config;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoderConfig {
    
    @Bean 
    public Decoder base64Decoder() {
        return Base64.getDecoder();
    }
}

package com.example.ridesservice.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class KeycloakConfig {
    private String resourceId;
    private String principalAttribute;
}

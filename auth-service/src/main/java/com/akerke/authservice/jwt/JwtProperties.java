package com.akerke.authservice.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
@ToString
public class JwtProperties {

    private String issuer;
    private Map<TokenType, String> tokens;

}

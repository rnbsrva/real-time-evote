package com.akerke.authservice.jwt;

import lombok.Getter;

@Getter
public enum TokenType {

    REFRESH_TOKEN("refresh-token", 3_600_000L),
    ACCESS_TOKEN("access-token", 300_000L);

    private final String name;
    private final Long expirationAmount;

    TokenType(String name, Long expirationAmount) {
        this.name = name;
        this.expirationAmount = expirationAmount;
    }
}

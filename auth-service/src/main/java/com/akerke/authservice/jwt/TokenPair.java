package com.akerke.authservice.jwt;

public record TokenPair (
        String accessToken,
        String refreshToken
) {
}

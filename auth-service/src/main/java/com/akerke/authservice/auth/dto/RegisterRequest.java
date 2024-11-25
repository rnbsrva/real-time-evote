package com.akerke.authservice.auth.dto;

public record RegisterRequest (
        String name,
        String surname,
        String phoneNumber,
        String email,
        String password
) {
}

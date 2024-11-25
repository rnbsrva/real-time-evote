package com.akerke.authservice.auth.dto;

public record AuthRequest (
        String email,
        String password
) {
}

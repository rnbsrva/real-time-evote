package com.akerke.authservice.auth;

import com.akerke.authservice.auth.dto.AuthRequest;
import com.akerke.authservice.auth.dto.RegisterRequest;
import com.akerke.authservice.jwt.TokenPair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    TokenPair register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    TokenPair login(
            @RequestBody AuthRequest authRequest
    ) {
        return authService.login(authRequest);
    }

//    @PostMapping
//    TokenPair refreshToken() {
//        return refreshToken();
//    } TODO:

    // TODO:forgot, reset password

}

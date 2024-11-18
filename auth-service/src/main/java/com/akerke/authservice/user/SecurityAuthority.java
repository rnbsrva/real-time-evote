package com.akerke.authservice.user;

import org.springframework.security.core.GrantedAuthority;

public enum SecurityAuthority implements GrantedAuthority {
    ADMIN, USER, MODERATOR;


    @Override
    public String getAuthority() {
        return this.name();
    }
}

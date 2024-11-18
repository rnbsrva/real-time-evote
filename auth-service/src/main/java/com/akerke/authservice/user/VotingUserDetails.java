package com.akerke.authservice.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record VotingUserDetails(VotingUser votingUser) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return votingUser.getAuthorities();
    }

    @Override
    public String getPassword() {
        return votingUser.getPassword();
    }

    @Override
    public String getUsername() {
        return votingUser.getEmail();
    }
}
package com.akerke.authservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingUserDetailsService implements UserDetailsService {

    private final VotingUserRepository votingUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return votingUserRepository.findByEmail(username)
                .map(VotingUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

package com.akerke.authservice.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class VotingUser {

    @Id
    private String id;

    private String name;
    private String surname;

    private String phoneNumber;

    private String email;
    private String password;

    private List<SecurityAuthority> authorities;

}

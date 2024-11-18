package com.akerke.authservice.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingUserRepository extends MongoRepository<VotingUser, String> {

    Optional<VotingUser> findByEmail(String email);

}

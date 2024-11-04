package com.akerke.votingservice.vote.dto;

public record VoteDTO (
        String pollId,
        String optionId,
        String email
){}
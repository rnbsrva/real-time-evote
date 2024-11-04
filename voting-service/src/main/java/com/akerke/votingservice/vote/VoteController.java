package com.akerke.votingservice.vote;

import com.akerke.votingservice.vote.dto.VoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;


    @MutationMapping
    @ResponseStatus(value = HttpStatus.OK)
    Boolean vote(
            @Argument VoteDTO voteDTO
            ){
        voteService.vote(voteDTO);
        return true;
    }

}

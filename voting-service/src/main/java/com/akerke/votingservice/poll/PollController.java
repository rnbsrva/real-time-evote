package com.akerke.votingservice.poll;

import com.akerke.votingservice.poll.dto.PollSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@Controller
public class PollController {

    private final PollService pollService;

        @MutationMapping()
        @ResponseStatus(value = HttpStatus.CREATED)
        Poll savePoll(
                @Argument PollSaveDTO pollSaveDTO
                ) {
            return pollService.savePoll(pollSaveDTO);
        }

}

package com.akerke.votingservice.poll.dto;

import java.util.List;

public record PollSaveDTO(
        String name,
        List<String> options
) {
}

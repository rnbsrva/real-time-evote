package com.akerke.votingservice.vote;

import com.akerke.votingservice.vote.dto.VoteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VoteListener {


    @KafkaListener(topics = "votes", groupId = "vote_group")
    public void listen(VoteDTO voteDTO) {
        log.info("Received vote: " + voteDTO);
    }
}
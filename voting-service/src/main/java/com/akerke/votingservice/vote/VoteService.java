package com.akerke.votingservice.vote;

import com.akerke.votingservice.common.KafkaMessageSender;
import com.akerke.votingservice.common.Topic;
import com.akerke.votingservice.vote.dto.VoteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
class VoteService {

    private final VoteRepository voteRepository;

    private final KafkaMessageSender kafkaMessageSender;

    void vote(VoteDTO voteDTO) {
        log.info("Voting on poll: {}, option: {}, by email: {}", voteDTO.pollId(), voteDTO.optionId(), voteDTO.email());

        Vote vote = new Vote();
        vote.setCreatedDate(LocalDateTime.now());
        vote.setPollId(voteDTO.pollId());
        vote.setOptionId(voteDTO.optionId());
        vote.setEmail(voteDTO.email());

        voteRepository.save(vote);

        kafkaMessageSender.sendMessage(Topic.VOTES.name(), voteDTO);
    }
}

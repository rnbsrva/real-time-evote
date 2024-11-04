package com.akerke.votingservice.vote;

import com.akerke.votingservice.vote.dto.VoteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
class VoteService {

    private final VoteRepository voteRepository;

    private final KafkaTemplate<String, VoteDTO> kafkaTemplate;

    private static final String VOTE_TOPIC = "votes";

    void vote(VoteDTO voteDTO) {
        log.info("Voting on poll: {}, option: {}, by email: {}", voteDTO.pollId(), voteDTO.optionId(), voteDTO.email());

        Vote vote = new Vote();
        vote.setCreatedDate(LocalDateTime.now());
        vote.setPollId(voteDTO.pollId());
        vote.setOptionId(voteDTO.optionId());
        vote.setEmail(voteDTO.email());

        voteRepository.save(vote);

        CompletableFuture<SendResult<String, VoteDTO>> future = kafkaTemplate.send(VOTE_TOPIC, voteDTO);

        future.thenAccept(result -> {
            log.info("Vote sent successfully: {}", result.getProducerRecord());
        }).exceptionally(ex -> {
            log.error("Failed to send vote: {}", ex.getMessage());
            return null;
        });
    }
}

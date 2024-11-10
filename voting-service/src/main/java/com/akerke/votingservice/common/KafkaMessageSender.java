package com.akerke.votingservice.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage (String topic, Object message) {
        kafkaTemplate.send(topic, message).thenAccept(result -> {
            log.info("Vote sent successfully: {}", result.getProducerRecord());
        }).exceptionally(ex -> {
            log.error("Failed to send vote: {}", ex.getMessage());
            return null;
        });
    }

}

package com.akerke.notificationservice.notification

import com.akerke.notificationservice.notification.dto.VoteDTO
import lombok.extern.slf4j.Slf4j
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@Slf4j
class VoteListener (
        private val notificationService: NotificationService
) {

    @KafkaListener(topics = ["votes"], groupId = "vote_group")
    fun listen(voteDTO: VoteDTO) {
        notificationService.checkForNotifications(voteDTO);
    }

}
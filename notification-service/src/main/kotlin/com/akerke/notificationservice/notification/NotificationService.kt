package com.akerke.notificationservice.notification

import com.akerke.notificationservice.notification.dto.VoteDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class NotificationService(
        private val notificationPublisher: NotificationPublisher,
        @Value("app.notify-threshold")
        private val VOTE_THRESHOLD : Int
) {

    private val voteCount: MutableMap<String, Int> = ConcurrentHashMap()

    fun sendNotificationWhenReachThreshold(vote: VoteDTO) {
        val currentCount = voteCount.merge(vote.optionId, 1, Int::plus) ?: 1
        if (currentCount == VOTE_THRESHOLD) {
            val notificationText = "Option ${vote.optionId} has reached $VOTE_THRESHOLD votes!"
            notificationPublisher.sendNotification(notificationText)
        }
    }

}
package com.akerke.notificationservice.notification

import com.akerke.notificationservice.notification.dto.Notification
import lombok.Setter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
@Setter
class NotificationPublisher @Autowired constructor(
        private val messagingTemplate: SimpMessagingTemplate
){

    fun sendNotification(message: String) {
        val notification = Notification(message)
        messagingTemplate.convertAndSend("/topic/notifications", notification)
    }

}
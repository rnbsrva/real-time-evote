package com.akerke.notificationservice.notification.dto

data class VoteDTO(
        val pollId: String,
        val optionId: String,
        val email: String
)
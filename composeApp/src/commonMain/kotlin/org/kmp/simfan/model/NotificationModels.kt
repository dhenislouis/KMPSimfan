package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val id: UInt,
    val title: String,
    val message: String,
    val date: String,
    val isRead: Boolean
)
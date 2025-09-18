package org.kmp.simfan.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CameraError(
    @SerialName("code") val code: Int,
    @SerialName("message") val message: String,
)
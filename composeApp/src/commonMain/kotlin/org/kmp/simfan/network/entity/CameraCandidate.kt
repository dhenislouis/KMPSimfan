package org.kmp.simfan.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CameraCandidate(
    @SerialName("content") val content: CameraContent
)
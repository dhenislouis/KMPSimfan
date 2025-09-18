package org.kmp.simfan.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CameraContent(
    @SerialName("parts") val parts: List<CameraPart>
) {

    @Serializable
    data class CameraPart(
        @SerialName("text") val text: String
    )
}
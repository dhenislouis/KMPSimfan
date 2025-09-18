package org.kmp.simfan.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CameraResponse(
    @SerialName("candidates") val candidates: List<CameraCandidate> = emptyList(),
    @SerialName("error") val error: CameraError? = null
)
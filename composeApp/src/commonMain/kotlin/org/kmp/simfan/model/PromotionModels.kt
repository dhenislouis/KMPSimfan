package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class Promotion(
    val id: UInt,
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val cashback: Double,
    val voucher: String?,
    val status: Boolean
)
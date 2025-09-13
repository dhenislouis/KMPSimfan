package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: UInt,
    val name: String,
    val email: String?,
    val phone: String?
)

@Serializable
data class SaldoResponse(
    val saldo: Double,
    val lastUpdated: String
)

@Serializable
data class SimulasiDepositoResponse(
    val amount: Double,
    val interestRate: Double,
    val tenor: Int,
    val estimatedReturn: Double
)
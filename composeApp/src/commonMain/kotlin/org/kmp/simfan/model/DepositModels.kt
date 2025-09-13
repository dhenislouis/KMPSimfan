package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class Deposit(
    val id: UInt,
    val deposanId: UInt,
    val productId: UInt,
    val amount: Double,
    val tenor: Int,
    val startDate: String,
    val maturityDate: String,
    val interestRate: Double,
    val aro: Boolean,
    val status: String,
    val transactionNo: String,
    val docUrl: String?,
    val signedDocUrl: String?,
    val signStatus: String
)

@Serializable
data class DepositRequest(
    val deposanId: UInt,
    val productId: UInt,
    val amount: Double,
    val tenor: Int,
    val aro: Boolean
)
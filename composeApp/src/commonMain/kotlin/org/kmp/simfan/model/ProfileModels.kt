package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: UInt,
    val deposanName: String,
    val deposanEmail: String,
    val deposanPhone: String,
    val revenueName: String,
    val identityNumber: String,
    val status: String
)

@Serializable
data class BankAccount(
    val id: UInt,
    val bankName: String,
    val accountNumber: String,
    val accountHolder: String,
    val isPrimary: Boolean
)
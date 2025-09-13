package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: UInt,
    val name: String,
    val description: String?,
    val bprName: String,
    val bprId: UInt,
    val minAmount: Double,
    val interestRate: Double,
    val tenor: Int,
    val aro: Boolean,
    val status: Boolean
)

@Serializable
data class BPR(
    val id: UInt,
    val name: String,
    val location: String,
    val logoURL: String,
    val productCode: String,
    val ojkCode: String,
    val isGuaranteedLPS: Boolean,
    val depositLimit: Double,
    val transactionsTotal: Int,
    val createdBy: UInt,
    val status: Boolean
)
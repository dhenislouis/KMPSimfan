package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val message: String,
    val data: T? = null,
    val error: String? = null
)

@Serializable
data class PaginatedResponse<T>(
    val message: String,
    val data: List<T>,
    val meta: PaginatedMeta
)

@Serializable
data class PaginatedMeta(
    val total: Int,
    val totalPages: Int,
    val currentPage: Int,
    val perPage: Int
)
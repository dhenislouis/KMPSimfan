package org.kmp.simfan.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val userId: String,
    val password: String
)

@Serializable
data class SignUpRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)

@Serializable
data class SignInResponse(
    val accessToken: String
)

@Serializable
data class FirebaseTokenRequest(
    val token: String,
    val name: String
)
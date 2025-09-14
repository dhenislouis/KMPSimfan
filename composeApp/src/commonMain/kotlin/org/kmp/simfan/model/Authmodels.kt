package org.kmp.simfan.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("user_id")
    val userId: String,
    val password: String,
    @SerialName("remember_me")
    val rememberMe: Boolean
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
    val status: Boolean,
    val message: String,
    val data: SignInData? = null
)
@Serializable
data class ErrorResponse(
    val status: Boolean,
    val message: String,
)

@Serializable
data class SignInData(
    @SerialName("access_token")
    val accessToken: String
)

@Serializable
data class FirebaseTokenRequest(
    val token: String,
    val name: String
)
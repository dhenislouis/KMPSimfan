package org.kmp.simfan.screen.auth

interface AuthService {
    fun sendOtp(
        phoneNumber: String,
        onCodeSent: (verificationId: String) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}

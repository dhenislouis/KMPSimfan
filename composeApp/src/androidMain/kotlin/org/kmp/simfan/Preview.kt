package org.kmp.simfan

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.kmp.simfan.screen.auth.login.LoginScreen

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onBackClick= {},
        onLoginClick = {},
        onGoogleLoginClick = {},
        onForgotPasswordClick = {},
        onRegisterClick = {}
    )
}
package org.kmp.simfan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import org.kmp.simfan.auth.AuthManager
class MainActivity : ComponentActivity() {
    private lateinit var googleAuthClient: GoogleAuthClient

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen() // jangan kasih "this"!
        super.onCreate(savedInstanceState)
        googleAuthClient = GoogleAuthClient(this)
        setContent {
            val authManager = AuthManager()
            App(
                loginWithGoogle = { googleAuthClient.signInAndGetFirebaseIdToken() },
                authManager = authManager
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        loginWithGoogle = { org.kmp.simfan.model.FirebaseTokenRequest(
            token = "dummy_token",
            name = "no name"
        ) },
        authManager = AuthManager()
    )
}
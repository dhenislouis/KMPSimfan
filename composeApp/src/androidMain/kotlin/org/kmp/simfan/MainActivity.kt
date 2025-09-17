package org.kmp.simfan

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import org.kmp.simfan.presentation.auth.LoginViewModel

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val loginViewModel = LoginViewModel()
    private lateinit var googleAuthClient: GoogleAuthClient

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen() // jangan kasih "this"!
        super.onCreate(savedInstanceState)
        googleAuthClient = GoogleAuthClient(this)
        setContent {
            App(
                onGoogleLoginClick = { startGoogleSignIn() }
            )
        }
    }
    private fun startGoogleSignIn() {
        lifecycleScope.launch {
            try {
                loginViewModel.googleLogin(googleAuthClient.getGoogleIdToken())
            } catch (e: Exception) {
                // Handle cancel / error
                Toast.makeText(this@MainActivity, "Login dibatalkan atau gagal.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}


@Preview
@Composable
fun AppAndroidPreview() {
    App(onGoogleLoginClick = {})
}
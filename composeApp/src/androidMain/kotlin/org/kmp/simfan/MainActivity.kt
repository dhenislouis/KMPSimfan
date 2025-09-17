package org.kmp.simfan

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import androidx.activity.result.contract.ActivityResultContracts
import org.kmp.simfan.presentation.auth.LoginViewModel
class MainActivity : ComponentActivity() {
    private val loginViewModel = LoginViewModel()
    private lateinit var googleAuthClient: GoogleAuthClient
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            Log.d("AuthResult", "Menerima hasil sukses dari Google Sign-In.")
            val user = googleAuthClient.getSignedInUserFromIntent(result.data)

            if (user != null) {
                Log.d("AuthResult", "User: $user")
                loginViewModel.googleLogin(user)
            } else {
                Log.e("AuthResult", "Gagal mendapatkan idToken dari hasil intent.")
                Toast.makeText(this, "Gagal mendapatkan token.", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Log.w("AuthResult", "Alur login Google dibatalkan atau gagal. Kode: ${result.resultCode}")
            Toast.makeText(this, "Login dibatalkan.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startGoogleSignIn() {
        googleAuthClient.logOut {
            val signInIntent = googleAuthClient.getSignInIntent()
            googleSignInLauncher.launch(signInIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // jangan kasih "this"!
        super.onCreate(savedInstanceState)
        googleAuthClient = GoogleAuthClient(this)

        setContent {
            App(
                onGoogleLoginClick = {
                    startGoogleSignIn()
                }
            )
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App(onGoogleLoginClick = {})
}
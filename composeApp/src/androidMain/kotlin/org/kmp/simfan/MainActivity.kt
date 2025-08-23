package org.kmp.simfan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import org.kmp.simfan.screen.auth.login.LoginScreen
import org.kmp.simfan.screen.auth.password.LupaPasswordScreen
import org.kmp.simfan.screen.auth.password.VerifikasiEmailScreen
import org.kmp.simfan.screen.auth.password.VerifikasiSMSScreen
import org.kmp.simfan.screen.auth.password.NewPasswordScreen
import org.kmp.simfan.screen.auth.password.UbahPasswordScreen
import org.kmp.simfan.screen.auth.register.RegisterScreen
import org.kmp.simfan.screen.onboarding.OnboardingStep1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    Navigator(OnboardingStep1)
                }
            }
        }
    }
}

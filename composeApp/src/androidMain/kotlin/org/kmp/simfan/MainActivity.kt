package org.kmp.simfan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.kmp.simfan.screen.auth.login.LoginScreen
import org.kmp.simfan.screen.auth.password.LupaPasswordScreen
import org.kmp.simfan.screen.auth.password.VerifikasiEmailScreen
import org.kmp.simfan.screen.auth.password.VerifikasiSMSScreen
import org.kmp.simfan.screen.auth.register.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    VerifikasiSMSScreen(
                        onBackClick = {},
                        onResendClick = {},
                        onVerifyClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    MaterialTheme {
        Surface {
            VerifikasiSMSScreen(
                onBackClick = {},
                onResendClick = {},
                onVerifyClick = {}
            )
        }
    }
}

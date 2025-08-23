package org.kmp.simfan.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.kmp.simfan.screen.onboarding.component.ContentOnboarding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.onboard4

object OnboardingStep4 : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showDialog by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.onboard4),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Info") },
                    text = { Text("Clicked") },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 40.dp)
            ) {
                ContentOnboarding(
                    label = "Get Started with Simfan",
                    title = "Mulai Simpan & Investasi Sekarang",
                    description = "Buka akunmu sekarang, nikmati bunga kompetitif, dan kelola deposito dengan mudah langsung dari aplikasi.",
                    buttonText = "Lanjut",
                    onButtonClick = {
                        // setelah step terakhir → arahkan ke dashboard/login dsb.
                        // contoh: navigator.push(LoginScreen)
                        showDialog = true
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingStep4Preview() {
    OnboardingStep4.Content()
}

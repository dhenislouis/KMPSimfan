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
import simfan.composeapp.generated.resources.onboard2

object OnboardingStep2 : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showDialog by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.onboard2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Info") },
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
                    label = "Special Rate Offer",
                    title = "6.25% Bunga Kompetitif Keuntungan Maksimal",
                    description = "Dapatkan bunga tinggi untuk hasil maksimal dari dana yang kamu simpan. Pilihan ideal untuk pertumbuhan keuanganmu.",
                    buttonText = "Lanjut",
                    onButtonClick = {
                        // nanti ganti ke step berikutnya, misalnya Step3
                        // navigator.push(OnboardingStep3)
                        showDialog = true
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingStep2Preview() {
    OnboardingStep2.Content()
}

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
import org.kmp.simfan.screen.onboarding.component.ContentOnboarding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.onboard1

object OnboardingStep1 : Screen {

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.onboard1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            var showDialog by remember { mutableStateOf(false) }

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
                    label = "Introducing Simpanan Deposito App",
                    title = "100% Online dengan Proses Praktis dan Cepat",
                    description = "Buka deposito langsung dari aplikasi tanpa harus ke kantor cabang. Simpan dana dengan lebih mudah, cepat, dan efisien.",
                    buttonText = "Lanjut",
                    onButtonClick = {
                        // TODO: nanti ganti dengan push ke OnboardingStep2
                        showDialog = true
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingStep1Preview() {
    OnboardingStep1.Content()
}

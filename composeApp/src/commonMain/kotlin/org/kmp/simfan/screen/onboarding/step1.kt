package org.kmp.simfan.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import org.kmp.simfan.screen.onboarding.component.ContentOnboarding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Primary
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.onboard1

@Composable
fun OnboardingStep1(
    onNextClick: () -> Unit,

    ) {
    Box(
        modifier = Modifier.fillMaxSize().background(Primary)
    ) {
        // Background Image

        AsyncImage(
            model = Res.getUri("files/onboard1.svg"),
            contentDescription = "onboard1",
            modifier = Modifier.fillMaxSize(),
        )

        // Konten di bawah
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
                    onNextClick()
                }
            )
        }
    }
}


@Preview()
@Composable
fun OnboardingStep1Preview() {
    OnboardingStep1(
        onNextClick = {}
    )
}
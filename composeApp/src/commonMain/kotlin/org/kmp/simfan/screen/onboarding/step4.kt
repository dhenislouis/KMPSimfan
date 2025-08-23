package org.kmp.simfan.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.kmp.simfan.screen.onboarding.component.ContentOnboarding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.onboard4

@Composable
fun OnboardingStep4(
    onNextClick: () -> Unit,

    ) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(Res.drawable.onboard4),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        val showDialog = remember { mutableStateOf(false) }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = {
                    Text(text = "Info")
                },
                text = {
                    Text("Clicked")
                },
                confirmButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("OK")
                    }
                }
            )
        }
        // Konten di bawah
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 40.dp)
        ) {
            ContentOnboarding(
                label = "Protected by LPS & Trusted Banks",
                title = "100% Aman oleh LPS & Bank Terpercaya",
                description = "Dana dijamin LPS dan dikelola oleh bank mitra terpercaya untuk perlindungan dan ketenangan maksimal.",
                buttonText = "Lanjut",
                onButtonClick = {
                    showDialog.value = true
                }
            )
        }
    }
}


@Preview()
@Composable
fun OnboardingStep4Preview() {
    OnboardingStep4(
        onNextClick = {}
    )
}
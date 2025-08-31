package org.kmp.simfan.components.value

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.components.navbar.TopBar
import org.kmp.simfan.components.number.NumDisplay
import org.kmp.simfan.components.value.Numpad
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

// 🚀 Voyager Screen
object InputPinScreen : Screen {
    @Composable
    override fun Content() {
        InputPinUI()
    }
}

@Composable
fun InputPinUI(
    onBackClick: () -> Unit = {},
    onNext: () -> Unit = {},
    onForgotPin: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }
    var showPin by remember { mutableStateOf(false) }
    var showKeyboard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        TopBar(title = "PIN", onBackClick = onBackClick)

        // Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Masukkan PIN", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Text(
                "Silahkan Masukkan PIN Anda",
                fontSize = 11.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            )

            Spacer(Modifier.height(16.dp))

            // PIN Display
            NumDisplay(
                value = pin,
                showValue = showPin,
                isOtp = false,
                onClick = { showKeyboard = true },
                valueLength = 6
            )

            IconButton(onClick = { showPin = !showPin }) {
                Icon(
                    painter = painterResource(
                        if (showPin) Res.drawable.eye_off else Res.drawable.eye_on
                    ),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(28.dp)
                )
            }

            TextButton(onClick = onForgotPin) {
                Text("Lupa PIN?", color = Color(0xFF2563EB))
            }
        }

        // Numpad muncul hanya jika showKeyboard
        if (showKeyboard) {
            Numpad(
                value = pin,
                onValueChange = { pin = it },
                maxLength = 6,
                onNext = onNext
            )
        }
    }
}

@Preview
@Composable
fun PreviewInputPinUI() {
    InputPinUI()
}


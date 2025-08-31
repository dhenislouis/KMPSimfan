package org.kmp.simfan.components.value

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.kmp.simfan.components.dialog.SuccessDialog
import org.kmp.simfan.components.navbar.TopBar
import org.kmp.simfan.components.number.NumDisplay
import org.kmp.simfan.components.value.Numpad
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

// 🚀 Voyager Screen
object InputOtpScreen : Screen {
    @Composable
    override fun Content() {
        InputOtpUI()
    }
}

@Composable
fun InputOtpUI(
    onBackClick: () -> Unit = {},
    onNext: () -> Unit = {},
    onVerify: (String) -> Unit = {},
    onResend: () -> Unit = {}
) {
    var otp by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }
    var showKeyboard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        TopBar(title = "Verifikasi", onBackClick = onBackClick)

        // Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Masukkan Kode Verifikasi", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Text(
                "Silakan ketik kode verifikasi yang dikirim ke ayuucinta@gmail.com",
                fontSize = 11.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            Spacer(Modifier.height(16.dp))

            // PIN Display
            NumDisplay(
                value = otp,
                showValue = true,
                isOtp = true,
                onClick = { showKeyboard = true },
                valueLength = 4
            )

            Button(
                onClick = {
                    onVerify(otp)
                    onNext()
                    showKeyboard = false
                    showSuccess = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = otp.length == 4,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (otp.length == 4) Color(0xFF2563EB) else Color.Gray
                )
            ) {
                Text("Verifikasi", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(16.dp))

            // 🔹 Resend Code
            TextButton(onClick = onResend) {
                Text("Kirim Ulang Kode", color = Color(0xFF2563EB))
            }
        }

        // Numpad muncul hanya jika showKeyboard
        if (showKeyboard) {
            Numpad(
                value = otp,
                onValueChange = { otp = it },
                maxLength = 4,
                onNext = {
                    onNext()
                    showKeyboard = false
                }
            )
        }
        if (showSuccess) {
            SuccessDialog(
                title = "Tanda Tangan Berhasil",
                message = "Tanda tangan digital Anda telah berhasil \ndiverifikasi oleh Privy.",
                buttonText = "Lanjut",
                onConfirm = {
                    showSuccess = false
                    onNext()
                },
                onDismiss = { showSuccess = false }
            )
        }
    }
}

@Preview
@Composable
fun PreviewInputOtpUI() {
    InputOtpUI()
}

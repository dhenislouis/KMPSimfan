package org.kmp.simfan.screen.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.email_illustration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterVerifikasiBottomSheet(
    navController: NavController,
    currentRoute: Routes?,
    onDismiss: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    var kodeVerifikasi by remember { mutableStateOf("09861") }
    val sisaWaktu = "3:03"

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ilustrasi di atas
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color(0xFFF5F6FA), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.email_illustration),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // Judul
            Text(
                text = "Konfirmasi Email Anda",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            // Subjudul
            Text(
                text = "Kami telah mengirimkan kode verifikasi 5 digit ke ayucintiaputri@gmail.com",
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(24.dp))

            // Label
            Text(
                text = "Email",
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 6.dp)
            )

            // Input Kode + tombol resend
            OutlinedTextField(
                value = kodeVerifikasi,
                onValueChange = { kodeVerifikasi = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE5E7EB),
                    focusedBorderColor = Color(0xFF4F7DF3)
                ),
                trailingIcon = {
                    TextButton(onClick = { /* TODO: resend code */ }) {
                        Text(
                            text = "Resend in $sisaWaktu",
                            fontSize = 12.sp,
                            color = Color(0xFF4F7DF3)
                        )
                    }
                }
            )

            Spacer(Modifier.height(28.dp))

            // Tombol
            Button(
                onClick = onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4F7DF3)
                )
            ) {
                Text(
                    text = "Verifikasi dan Buat Akun",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

//@Preview()
//@Composable
//fun PreviewRegisterVerifikasiBottomSheet() {
//    RegisterVerifikasiBottomSheet()
//}

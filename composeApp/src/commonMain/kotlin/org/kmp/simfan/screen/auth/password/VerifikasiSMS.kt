package org.kmp.simfan.screen.auth.password

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back

@Composable
fun VerifikasiSMSScreen(
    phoneNumber: String = "081234567899",
    onBackClick: () -> Unit = {},
    onResendClick: () -> Unit = {},
    onVerifyClick: (String) -> Unit = {}
) {
    var digit1 by remember { mutableStateOf("") }
    var digit2 by remember { mutableStateOf("") }
    var digit3 by remember { mutableStateOf("") }
    var digit4 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "Verifikasi",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = {},
                enabled = false,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            ) {}
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Masukkan Kode Verifikasi",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 25.dp)
            )

            Text(
                text = "Silakan ketik kode verifikasi yang dikirim ke\n$phoneNumber",
                fontSize = 13.sp,
                color = Color(0xFF6B7280),
                lineHeight = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 55.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 55.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                SMSDigitTextField(digit1) { digit1 = it }
                SMSDigitTextField(digit2) { digit2 = it }
                SMSDigitTextField(digit3) { digit3 = it }
                SMSDigitTextField(digit4) { digit4 = it }
            }

            Text(
                text = "Kirim Ulang Kode Saya",
                fontSize = 13.sp,
                color = Color.Black,
                modifier = Modifier
                    .clickable { onResendClick() }
                    .padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    val code = digit1 + digit2 + digit3 + digit4
                    onVerifyClick(code)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = ButtonDefaults.buttonElevation(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF))
            ) {
                Text(
                    text = "Lanjut",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun SMSDigitTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1 && it.all { ch -> ch.isDigit() }) {
                onValueChange(it)
            }
        },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .size(56.dp),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview()
@Composable
fun VerifikasiSMSPreview() {
    VerifikasiSMSScreen()
}

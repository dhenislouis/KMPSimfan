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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

// 🚀 Voyager Screen
object NewPasswordScreen : Screen {

    @Composable
    override fun Content() {
        NewPasswordUI(
            onBackClick = { /* navigator.pop() */ },
            onConfirmClick = { /* TODO: handle confirm */ }
        )
    }
}

@Composable
fun NewPasswordUI(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
    ) {
        // AppBar
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
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Ubah Kata Sandi",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Body
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Silakan masukkan kata sandi baru Anda",
                fontSize = 13.sp,
                color = Color(0xFF252C32),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            PasswordInputField(
                label = "Password Baru",
                value = password,
                onValueChange = { password = it },
                placeholder = "Masukkan kata sandi baru",
                visible = passwordVisible,
                onToggleVisibility = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PasswordInputField(
                label = "Konfirmasi Kata Sandi Baru",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Masukkan kata sandi baru",
                visible = confirmPasswordVisible,
                onToggleVisibility = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF)),
                elevation = ButtonDefaults.buttonElevation(6.dp)
            ) {
                Text(
                    text = "Konfirmasi",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun PasswordInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            singleLine = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        if (visible) Res.drawable.eye_on else Res.drawable.eye_off
                    ),
                    contentDescription = "Toggle Password Visibility",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onToggleVisibility() },
                    tint = Color.Gray
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

@Preview
@Composable
fun NewPasswordScreenPreview() {
    NewPasswordUI(
        onBackClick = {},
        onConfirmClick = {}
    )
}

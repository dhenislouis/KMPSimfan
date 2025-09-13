package org.kmp.simfan.screen.auth.password

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.presentation.auth.LupaPasswordViewModel
import org.kmp.simfan.screen.profile.ProfileScreen
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

@Composable
fun UbahPasswordUI(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    val viewModel = remember { LupaPasswordViewModel() }
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var oldVisible by remember { mutableStateOf(false) }
    var newVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

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
                    modifier = Modifier.size(18.dp)
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
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UbahPasswordInputField(
                label = "Kata Sandi Lama",
                value = oldPassword,
                onValueChange = { oldPassword = it },
                placeholder = "Masukkan kata sandi lama",
                visible = oldVisible,
                onToggleVisibility = { oldVisible = !oldVisible }
            )

            Spacer(modifier = Modifier.height(12.dp))

            UbahPasswordInputField(
                label = "Kata Sandi Baru",
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = "Masukkan kata sandi baru",
                visible = newVisible,
                onToggleVisibility = { newVisible = !newVisible }
            )

            Spacer(modifier = Modifier.height(12.dp))

            UbahPasswordInputField(
                label = "Konfirmasi Kata Sandi Baru",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Masukkan kata sandi baru",
                visible = confirmVisible,
                onToggleVisibility = { confirmVisible = !confirmVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6FA)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Kata sandi minimal 8 karakter dan harus mengandung huruf besar, huruf kecil dan angka.",
                    fontSize = 12.sp,
                    color = Color(0xFF22242F),
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }

            if (newPassword != confirmPassword && newPassword.isNotBlank() && confirmPassword.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Password baru tidak cocok",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            errorMessage.value?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }

        // Footer
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (oldPassword.isNotBlank() &&
                        newPassword.isNotBlank() &&
                        confirmPassword.isNotBlank() &&
                        newPassword == confirmPassword) {
                        viewModel.changePassword(newPassword)
                        onSaveClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF)),
                elevation = ButtonDefaults.buttonElevation(6.dp),
                enabled = !isLoading.value &&
                        oldPassword.isNotBlank() &&
                        newPassword.isNotBlank() &&
                        confirmPassword.isNotBlank() &&
                        newPassword == confirmPassword
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text(
                        text = "Simpan",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun UbahPasswordInputField(
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

//@Preview
//@Composable
//fun UbahPasswordPreview() {
//    UbahPasswordUI(
//        onBackClick = {},
//        onSaveClick = {}
//    )
//}

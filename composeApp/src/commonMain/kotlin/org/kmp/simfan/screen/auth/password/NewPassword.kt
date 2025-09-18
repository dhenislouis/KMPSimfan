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
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Button1
import org.kmp.simfan.presentation.auth.LupaPasswordViewModel
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

@Composable
fun NewPasswordUI(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    val viewModel = remember { LupaPasswordViewModel() }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Scaffold(
        topBar = {
            // ===== APPBAR =====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_back),
                        contentDescription = "Kembali",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Ubah Kata Sandi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f)) // biar title center
            }
        }
    ) { innerPadding ->
        // ===== BODY =====
        NewPasswordContent(
            modifier = Modifier.padding(innerPadding),
            password = password,
            onPasswordChange = { password = it },
            passwordVisible = passwordVisible,
            onTogglePasswordVisibility = { passwordVisible = !passwordVisible },
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            confirmPasswordVisible = confirmPasswordVisible,
            onToggleConfirmPasswordVisibility = { confirmPasswordVisible = !confirmPasswordVisible },
            isLoading = isLoading.value,
            errorMessage = errorMessage.value,
            onConfirmClick = {
                if (password == confirmPassword && password.isNotBlank()) {
                    viewModel.changePassword(password)
                    onConfirmClick()
                }
            }
        )
    }
}

@Composable
fun NewPasswordContent(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    confirmPasswordVisible: Boolean,
    onToggleConfirmPasswordVisibility: () -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    onConfirmClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .padding(horizontal = 24.dp, vertical = 35.dp),
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
            onValueChange = onPasswordChange,
            placeholder = "Masukkan kata sandi baru",
            visible = passwordVisible,
            onToggleVisibility = onTogglePasswordVisibility
        )

        Spacer(modifier = Modifier.height(12.dp))

        PasswordInputField(
            label = "Konfirmasi Kata Sandi Baru",
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = "Masukkan kata sandi baru",
            visible = confirmPasswordVisible,
            onToggleVisibility = onToggleConfirmPasswordVisibility
        )

        if (password != confirmPassword && password.isNotBlank() && confirmPassword.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Password tidak cocok",
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = onConfirmClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF)),
            elevation = ButtonDefaults.buttonElevation(6.dp),
            enabled = !isLoading &&
                    password.isNotBlank() &&
                    confirmPassword.isNotBlank() &&
                    password == confirmPassword
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
            } else {
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
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color(0xFFE0E0E0),
                focusedIndicatorColor = Button1,
                cursorColor = Color.Black,
            ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                color = Color.Black
            ),
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
//fun NewPasswordScreenPreview() {
//    NewPasswordUI(
//        onBackClick = {},
//        onConfirmClick = {}
//    )
//}

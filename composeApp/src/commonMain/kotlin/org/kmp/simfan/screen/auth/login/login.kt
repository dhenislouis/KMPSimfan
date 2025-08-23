package org.kmp.simfan.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import simfan.composeapp.generated.resources.ic_google

// 🚀 Voyager Screen
object LoginScreen : Screen {

    @Composable
    override fun Content() {
        LoginScreenUI()
    }
}

@Composable
fun LoginScreenUI(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {
        // Header
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
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Masuk ke Akun Anda", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Silahkan masuk dengan akun Anda", fontSize = 13.sp, color = Color(0xFF7F909F))
            }
            Spacer(modifier = Modifier.width(36.dp)) // biar header center
        }

        LoginContent(
            onLoginClick = onLoginClick,
            onGoogleLoginClick = onGoogleLoginClick,
            onForgotPasswordClick = onForgotPasswordClick,
            onRegisterClick = onRegisterClick
        )
    }
}

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Input nomor HP
        Column(modifier = Modifier.padding(bottom = 14.dp)) {
            Text("Nomor Handphone", fontSize = 14.sp, color = Color(0xFF6B7280))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                placeholder = { Text("Masukkan nomor handphone", fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
                singleLine = true
            )
        }

        // Input password
        Column(modifier = Modifier.padding(bottom = 14.dp)) {
            Text("Password", fontSize = 14.sp, color = Color(0xFF6B7280))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                placeholder = { Text("Password", fontSize = 14.sp, color = Color(0xFF9CA3AF)) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(if (passwordVisible) Res.drawable.eye_on else Res.drawable.eye_off),
                            contentDescription = "Toggle Password"
                        )
                    }
                }
            )
        }

        // Checkbox + Lupa password
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
            Text("Ingatkan Saya", fontSize = 12.sp, color = Color(0xFF999999), modifier = Modifier.padding(start = 4.dp))
            Spacer(modifier = Modifier.weight(1f))
            Text("Lupa password?", fontSize = 12.sp, color = Color(0xFF003FFC), modifier = Modifier.clickable { onForgotPasswordClick() })
        }

        // Button Login
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF))
        ) {
            Text("Masuk", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }

        // Divider
        HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth(), color = Color(0xFFE0E0E0))

        // Google Login
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .clickable { onGoogleLoginClick() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(painter = painterResource(Res.drawable.ic_google), contentDescription = "Google", modifier = Modifier.size(32.dp), tint = Color.Unspecified)
            Text("Masuk dengan Email", fontSize = 12.sp, color = Color(0xFF999999), modifier = Modifier.padding(start = 12.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Footer register
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Belum punya akun?", fontSize = 14.sp, color = Color(0xFF7F909F))
            Text(" Buat Akun", fontSize = 14.sp, color = Color(0xFF003FFC), fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable { onRegisterClick() })
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenUI()
}

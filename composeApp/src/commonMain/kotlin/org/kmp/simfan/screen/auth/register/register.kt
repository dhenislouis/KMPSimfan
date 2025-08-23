package org.kmp.simfan.screen.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.ic_google

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    // Gunakan MaterialTheme dengan light scheme
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color(0xFFF6F6F6),
            surface = Color.White,
            onSurface = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.background)
        ) {
            RegisterAppBar(onBackClick = onBackClick)
            RegisterContent(
                onRegisterClick = onRegisterClick,
                onGoogleLoginClick = onGoogleLoginClick
            )
        }
    }
}

@Composable
fun RegisterAppBar(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp)
    ) {
        // Tombol Back
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

        // Title & Subtitle di tengah
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Buat Akun Baru",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Silahkan daftar dengan akun Anda",
                fontSize = 13.sp,
                color = Color(0xFF7F909F),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun RegisterContent(
    onRegisterClick: () -> Unit,
    onGoogleLoginClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var referal by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var acceptTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        InputNama(
            label = "Nama Lengkap",
            value = name,
            onValueChange = { name = it },
            placeholder = "Masukkan Nama Lengkap"
        )

        InputNoHp(
            label = "Nomor Handphone",
            value = phone,
            onValueChange = { phone = it },
            placeholder = "Masukkan nomor handphone"
        )

        InputEmail(
            label = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Masukkan email"
        )

        PasswordField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            visible = passwordVisible,
            onToggleVisibility = { passwordVisible = !passwordVisible }
        )

        PasswordField(
            label = "Konfirmasi Password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Konfirmasi Password",
            visible = confirmPasswordVisible,
            onToggleVisibility = { confirmPasswordVisible = !confirmPasswordVisible }
        )

        InputReferal(
            label = "Kode Referal",
            value = referal,
            onValueChange = { referal = it },
            placeholder = "Masukkan Kode Referal"
        )

        // Checkbox
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = { acceptTerms = it }
            )
            Text(
                text = "Ingatkan Saya",
                fontSize = 12.sp,
                color = Color(0xFF999999),
                modifier = Modifier.padding(start = 6.dp)
            )
        }

        // Tombol Register
        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF))
        ) {
            Text(
                text = "Buat Akun",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Divider
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text(
                text = "atau",
                fontSize = 10.sp,
                color = Color(0xFF999999),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }

        // Tombol Google
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                .clickable { onGoogleLoginClick() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_google),
                contentDescription = "Google",
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "Masuk dengan Google",
                fontSize = 13.sp,
                color = Color(0xFF444444),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

// Input Components
@Composable
fun InputNama(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun InputNoHp(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun InputEmail(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun InputReferal(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun PasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visible: Boolean,
    onToggleVisibility: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val iconText = if (visible) "🙈" else "👁"
            Text(
                text = iconText,
                modifier = Modifier.clickable { onToggleVisibility() }
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen()
}

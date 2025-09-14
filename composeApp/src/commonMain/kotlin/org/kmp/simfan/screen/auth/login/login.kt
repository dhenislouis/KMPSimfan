package org.kmp.simfan.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.presentation.auth.LoginViewModel
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenUI(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    val viewModel = remember { LoginViewModel() }
    var identifier by remember { mutableStateOf("") } // email/nomor HP
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading
    val loginResult = viewModel.loginResult
    val errorMessage = viewModel.errorMessage
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Handle login result
    LaunchedEffect(loginResult.value) {
        if (loginResult.value != null) {
            showSheet = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Masuk ke Akun Anda",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            "Silahkan masuk dengan akun Anda",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            // Email / HP
            Text("Email/Nomor Handphone", fontSize = 14.sp, color = Color.Black)
            Spacer(Modifier.height(4.dp))
            OutlinedTextField(
                value = identifier,
                onValueChange = { identifier = it },
                placeholder = { Text(
                    "Masukkan Email/Nomor Handphone",
                    fontSize = 12.sp,
                    color = Color.Gray
                    ) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Password
            Text("Password", fontSize = 14.sp, color = Color.Black)
            Spacer(Modifier.height(4.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(
                    "Masukkan Password",
                    fontSize = 12.sp,
                    color = Color.Gray
                ) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(
                                if (passwordVisible) Res.drawable.eye_on else Res.drawable.eye_off
                            ),
                            contentDescription = "Toggle Password",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            )

            Spacer(Modifier.height(8.dp))

            // Remember me + lupa password
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Button1,
                        uncheckedColor = Color.Gray
                    )
                )
                Text("Ingatkan Saya", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "Lupa password?",
                    fontSize = 14.sp,
                    color = Color(0xFF003FFC),
                    modifier = Modifier.clickable { onForgotPasswordClick() }
                )
            }

            Spacer(Modifier.height(20.dp))

            // Tombol masuk
            Button(
                onClick = {
                    viewModel.login(identifier, password, rememberMe)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                elevation = ButtonDefaults.buttonElevation( // 👈 ada shadow
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                ),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF)),
                enabled = !isLoading.value && identifier.isNotBlank() && password.isNotBlank()
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text("Masuk", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
            // Error handling
            errorMessage.value?.let {
                Spacer(Modifier.height(12.dp))
                Text("Login gagal: $it", color = Color.Red, fontSize = 14.sp)
            }
            // Jika berhasil login → tampilkan bottom sheet
            if (showSheet) {
                LaunchedEffect(Unit) {
                    showSheet = true
                }
            }

            Spacer(Modifier.height(20.dp))

            // Divider dengan teks
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(Modifier.weight(1f), color = Color(0xFFE0E0E0))
                Text("  Masuk dengan  ", fontSize = 12.sp, color = Color.Gray)
                HorizontalDivider(Modifier.weight(1f), color = Color(0xFFE0E0E0))
            }

            Spacer(Modifier.height(20.dp))

            // Google login
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
                Icon(
                    painter = painterResource(Res.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(Modifier.width(12.dp))
                Text("Masuk dengan Google", fontSize = 14.sp, color = Color.Black)
            }

            Spacer(Modifier.height(32.dp))

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Belum punya akun?", fontSize = 14.sp, color = Color.Gray)
                Text(
                    " Buat Akun",
                    fontSize = 14.sp,
                    color = Color(0xFF003FFC),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showSheet = false
                    viewModel.clearLoginResult()
                },
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                LoginSuccessScreen(
                    navController = navController,
                    currentRoute = Routes.Login,
                    onDismiss = {
                        scope.launch {
                            sheetState.hide()
                            showSheet = false
                            viewModel.clearLoginResult()
                        }
                    },
                    onContinueClick = {
                        scope.launch {
                            sheetState.hide()
                            showSheet = false
                            viewModel.clearLoginResult()
                            navController.navigate(Routes.LoginSyaratKetentuan)
                        }
                    }
                )
            }
        }
    }
}


//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreenUI()
//}
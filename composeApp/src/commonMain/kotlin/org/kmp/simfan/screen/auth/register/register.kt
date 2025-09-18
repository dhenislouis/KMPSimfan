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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.presentation.auth.RegisterViewModel
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on
import simfan.composeapp.generated.resources.ic_google

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenUI(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val viewModel = remember { RegisterViewModel() }

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var referal by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var acceptTerms by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading
    val registerResult = viewModel.registerResult
    val errorMessage = viewModel.errorMessage

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Handle register result
    LaunchedEffect(registerResult.value) {
        if (registerResult.value != null) {
            showSheet = true
        }
    }

    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color(0xFFF6F6F6),
            surface = Color.White,
            onSurface = Color.Black
        )
    ) {
        Scaffold(
            topBar = { RegisterAppBar(onBackClick = onBackClick) },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                RegisterContent(
                    navController = navController,
                    currentRoute = currentRoute,
                    name = name,
                    onNameChange = { name = it },
                    phone = phone,
                    onPhoneChange = { phone = it },
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = { confirmPassword = it },
                    referal = referal,
                    onReferalChange = { referal = it },
                    passwordVisible = passwordVisible,
                    onPasswordVisibleChange = { passwordVisible = it },
                    confirmPasswordVisible = confirmPasswordVisible,
                    onConfirmPasswordVisibleChange = { confirmPasswordVisible = it },
                    acceptTerms = acceptTerms,
                    onAcceptTermsChange = { acceptTerms = it },
                    isLoading = isLoading.value,
                    errorMessage = errorMessage.value,
                    onRegister = {
                        viewModel.register(name, email, phone, password)
                    },
                    onGoogleLoginClick = onGoogleLoginClick
                )
            }
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
                viewModel.clearRegisterResult()
            },
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            RegisterVerifikasiBottomSheet(
                navController = navController,
                currentRoute = Routes.RegisterVerifikasi,
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                        showSheet = false
                        viewModel.clearRegisterResult()
                    }
                },
                onSave = {
                    scope.launch {
                        sheetState.hide()
                        showSheet = false
                        viewModel.clearRegisterResult()
                        navController.navigate(Routes.BuatPin)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterAppBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Buat Akun Baru",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    "Silahkan daftar dengan akun Anda",
                    fontSize = 14.sp,
                    color = Color(0xFF7F909F),
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}

@Composable
fun RegisterContent(
    navController: NavController,
    currentRoute: Routes?,
    name: String,
    onNameChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    referal: String,
    onReferalChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit,
    confirmPasswordVisible: Boolean,
    onConfirmPasswordVisibleChange: (Boolean) -> Unit,
    acceptTerms: Boolean,
    onAcceptTermsChange: (Boolean) -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    onRegister: () -> Unit,
    onGoogleLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(24.dp))
        InputNama(name, onNameChange, "Masukkan Nama Lengkap")
        Spacer(Modifier.height(16.dp))
        InputNoHp(phone, onPhoneChange, "Masukkan nomor handphone")
        Spacer(Modifier.height(16.dp))
        InputEmail(email, onEmailChange, "Masukkan email")
        Spacer(Modifier.height(16.dp))
        PasswordField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Password",
            visible = passwordVisible,
            onToggleVisibility = { onPasswordVisibleChange(!passwordVisible) }
        )
        Spacer(Modifier.height(16.dp))
        PasswordField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = "Konfirmasi Password",
            visible = confirmPasswordVisible,
            onToggleVisibility = { onConfirmPasswordVisibleChange(!confirmPasswordVisible) }
        )
        Spacer(Modifier.height(16.dp))
        InputReferal(referal, onReferalChange, "Masukkan Kode Referal")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = onAcceptTermsChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Button1,
                    uncheckedColor = Color.Gray
                )
            )
            Text(
                "Ingatkan Saya",
                fontSize = 14.sp,
                color = Color(0xFF999999),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
        errorMessage?.let {
            Spacer(Modifier.height(8.dp))
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = onRegister,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 10.dp
            ),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF)),
            enabled = !isLoading &&
                    name.isNotBlank() &&
                    phone.isNotBlank() &&
                    email.isNotBlank() &&
                    password.isNotBlank() &&
                    confirmPassword.isNotBlank() &&
                    password == confirmPassword &&
                    acceptTerms
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
            } else {
                Text("Buat Akun", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text("  Atau  ", fontSize = 12.sp, color = Color.Gray)
            Divider(Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }

        Spacer(Modifier.height(20.dp))

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
    }
}

@Composable
fun InputNama(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Text("Nama Lengkap", fontSize = 16.sp, color = Color.Black)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
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
        )
    )
}

@Composable
fun InputNoHp(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Text("Nomor Handphone", fontSize = 16.sp, color = Color.Black)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
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
        )
    )
}

@Composable
fun InputEmail(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Text("Email", fontSize = 16.sp, color = Color.Black)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
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
        )
    )
}

@Composable
fun InputReferal(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Text("Kode Referal", fontSize = 16.sp, color = Color.Black)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
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
        )
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Text(placeholder, fontSize = 16.sp, color = Color.Black)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
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
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onToggleVisibility) {
                Icon(
                    painter = painterResource(
                        if (visible) Res.drawable.eye_on else Res.drawable.eye_off
                    ),
                    contentDescription = if (visible) "Hide password" else "Show password",
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}


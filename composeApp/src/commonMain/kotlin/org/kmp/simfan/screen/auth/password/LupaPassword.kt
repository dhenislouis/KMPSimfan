package org.kmp.simfan.screen.auth.password

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.presentation.auth.LupaPasswordViewModel
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back

@Composable
fun LupaPasswordScreenUI(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onNextClick: (String) -> Unit = {}
) {
    val viewModel = remember { LupaPasswordViewModel() }
    var selectedOption by remember { mutableStateOf("SMS") }
    val scrollState = rememberScrollState()
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Scaffold(
        topBar = {
            // ==== TOP BAR ====
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
                Text(
                    text = "Lupa Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(36.dp)) // biar judul tetap center
            }
        },
        bottomBar = {
            // ==== BOTTOM BAR ====
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        viewModel.requestOTP(selectedOption)
                        onNextClick(selectedOption)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF)),
                    enabled = isLoading.value
                ) {
                    if (isLoading.value) {
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                    } else {
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
    ) { innerPadding ->
        // ==== BODY CONTENT ====
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Jangan khawatir, hanya beberapa\nlangkah saja",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Pilih metode di bawah ini untuk memulihkan kata sandi Anda",
                fontSize = 13.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(bottom = 40.dp),
                lineHeight = 18.sp,
                textAlign = TextAlign.Center
            )

            OptionCard(
                title = "Via SMS",
                description = "Kode akan dikirim ke **** **** *512",
                selected = selectedOption == "SMS",
                onClick = { selectedOption = "SMS" }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OptionCard(
                title = "Via Email",
                description = "Kode akan dikirim ke *****@vktr.com",
                selected = selectedOption == "Email",
                onClick = { selectedOption = "Email" }
            )

            errorMessage.value?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun OptionCard(
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF212121),
                unselectedColor = Color(0xFF212121)
            )
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xFF212121))
            Text(description, fontSize = 14.sp, color = Color(0xFF797979))
        }
    }
}

//@Preview
//@Composable
//fun LupaPasswordPreview() {
//    LupaPasswordScreenUI()
//}

//package com.amary.my.ocr.gemini
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginScreenUI() {
//    var identifier by remember { mutableStateOf("") } // email/nomor HP
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            "Masuk ke Akun Anda",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = Color.Black,
//                            textAlign = TextAlign.Center
//                        )
//                        Text(
//                            "Silahkan masuk dengan akun Anda",
//                            fontSize = 14.sp,
//                            color = Color.Gray,
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFF6F6F6))
//                .padding(innerPadding)
//                .padding(horizontal = 20.dp)
//                .verticalScroll(rememberScrollState())
//        ) {
//            Spacer(Modifier.height(24.dp))
//
//            // Email / HP
//            Text("Email/Nomor Handphone", fontSize = 14.sp, color = Color.Black)
//            Spacer(Modifier.height(4.dp))
//
//            OutlinedTextField(
//                value = identifier,
//                onValueChange = { identifier = it },
//                placeholder = {
//                    Text(
//                        "Masukkan Email/Nomor Handphone",
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .defaultMinSize(minHeight = 56.dp),
//                shape = RoundedCornerShape(12.dp),
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                    focusedContainerColor = Color.White,
//                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
//                    focusedIndicatorColor = Color(0xFFE0E0E0),
//                    cursorColor = Color.Black,
//                ),
//                textStyle = LocalTextStyle.current.copy(
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreenUI()
//}
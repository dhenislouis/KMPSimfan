package org.kmp.simfan.screen.profile.akunbank

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahBankScreen(
    navController: NavController, currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onTambahBank: () -> Unit = {}
) {
    val bankList = listOf("BCA", "Mandiri", "BRI", "BNI", "CIMB Niaga")
    var expanded by remember { mutableStateOf(false) }
    var selectedBank by remember { mutableStateOf("") }
    var nomorRekening by remember { mutableStateOf("") }
    var namaPemilik by remember { mutableStateOf("") }
    var agreed by remember { mutableStateOf(false) }

    val isFormValid = selectedBank.isNotEmpty() && nomorRekening.isNotBlank() && agreed

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tambah Bank",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            Button(
                onClick = onTambahBank,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF6E8BFE) else Button1
                ),
                enabled = isFormValid
            ) {
                Text(
                    text = "Tambah Bank",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
        ) {
            // Pilih bank (Dropdown)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                Text("Pilih Bank", fontSize = 14.sp, color = Color(0xFF6B7280))
                OutlinedTextField(
                    value = selectedBank,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Pilih bank") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    bankList.forEach { bank ->
                        DropdownMenuItem(
                            text = { Text(bank) },
                            onClick = {
                                selectedBank = bank
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Nomor Rekening", fontSize = 14.sp, color = Color(0xFF6B7280))
            OutlinedTextField(
                value = nomorRekening,
                onValueChange = { nomorRekening = it },
                label = { Text("Nomor Rekening") },
                placeholder = { Text("Masukkan nomor rekening") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Nama Pemilik Rekening", fontSize = 14.sp, color = Color(0xFF6B7280))
            OutlinedTextField(
                value = namaPemilik,
                onValueChange = { namaPemilik = it },
                label = { Text("Nama Lengkap") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Simpan & Setuju
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "Simpan & Setuju",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Dengan menyimpan rekening/tabungan ini, Anda menyetujui proses verifikasi dan otorisasi yang menjamin pengalaman transaksi yang aman dan nyaman.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = agreed,
                        onCheckedChange = { agreed = it }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Saya setuju dengan ",
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Syarat & Ketentuan",
                        fontSize = 13.sp,
                        color = Color(0xFF6E8BFE),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { }
                    )
                    Text(
                        text = " berlaku.",
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewTambahBankScreen() {
//    TambahBankScreen()
//}

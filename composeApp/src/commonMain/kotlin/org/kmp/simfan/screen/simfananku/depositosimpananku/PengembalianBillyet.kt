package org.kmp.simfan.screen.simfananku.depositosimpananku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.arrow_down

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengembalianBilyetScreen() {
    var selectedEkspedisi by remember { mutableStateOf("") }
    var nomorResi by remember { mutableStateOf("") }
    var namaPengirim by remember { mutableStateOf("Syakira Putri") }
    var expanded by remember { mutableStateOf(false) }

    val ekspedisiList = listOf("JNE", "SiCepat", "J&T", "POS Indonesia")

    // Validasi: tombol aktif hanya jika semua field terisi
    val isFormValid = selectedEkspedisi.isNotBlank() && nomorResi.isNotBlank() && namaPengirim.isNotBlank()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pengembalian Bilyet") },
                navigationIcon = {
                    IconButton(onClick = { /* back */ }) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { /* Kirim action */ },
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF6589FF) else Color(0xFFBDBDBD),
                    disabledContainerColor = Color(0xFFBDBDBD),
                    contentColor = Color.White,
                    disabledContentColor = Color.White
                )
            ) {
                Text("Kirim", fontSize = 16.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(innerPadding)
        ) {
            // Card putih sebagai container form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // Pilih Ekspedisi
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedEkspedisi,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Pilih Ekspedisi") },
                            placeholder = { Text("Pilih jasa ekspedisi") },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(Res.drawable.arrow_down),
                                    contentDescription = null
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            ekspedisiList.forEach { ekspedisi ->
                                DropdownMenuItem(
                                    text = { Text(ekspedisi) },
                                    onClick = {
                                        selectedEkspedisi = ekspedisi
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Nomor Resi
                    OutlinedTextField(
                        value = nomorResi,
                        onValueChange = { nomorResi = it },
                        label = { Text("Nomor Resi") },
                        placeholder = { Text("Masukkan nomor resi") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    // Nama Pengirim
                    OutlinedTextField(
                        value = namaPengirim,
                        onValueChange = { namaPengirim = it },
                        label = { Text("Nama Pengirim") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPengembalianBilyetScreen() {
    PengembalianBilyetScreen()
}

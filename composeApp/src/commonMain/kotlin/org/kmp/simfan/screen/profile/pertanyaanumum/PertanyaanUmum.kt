package org.kmp.simfan.screen.profile.pertanyaanumum

import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PertanyaanUmumScreen(
    onBack: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        // 🔹 AppBar
        TopAppBar(
            title = { Text("Pertanyaan Umum", fontWeight = FontWeight.SemiBold, fontSize = 20.sp) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        // 🔹 Scroll Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // Header + Toggle
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                    ) {
                        Text(
                            text = "Data Diri",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Informasi pribadi seperti nama, tempat tanggal lahir, jenis kelamin, dan kewarganegaraan.",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 20.dp)
                            )

                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = "Toggle",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Expanded Content
                    if (expanded) {
                        Spacer(modifier = Modifier.height(16.dp))

                        DataItem("Nomor KTP", "872367489012")
                        DataItem("Nomor Handphone", "0872367489012")
                        DataItem("Email", "clarasaputri@gmail.com")
                        DataItem("Nama", "Clara Saputri")
                        DataItem("Tempat Lahir", "Jakarta")
                        DataItem("Tanggal Lahir", "17 Maret 2003")
                        DataItem("Jenis Kelamin", "Perempuan")
                        DataItem("Status Perkawinan", "Cerai Mati")
                    }
                }
            }
        }
    }
}

@Composable
fun DataItem(label: String, value: String) {
    Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
    Text(
        text = value,
        fontSize = 13.sp,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Preview()
@Composable
fun PreviewPertanyaanUmumScreen() {
    PertanyaanUmumScreen()
}

package org.kmp.simfan.screen.bukarekening

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.Primary
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LangkahScreen(
    onBack: () -> Unit = {},
    onLanjut: () -> Unit = {},
    onSyaratClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Langkah Buka Rekening",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                actions = {
                    Spacer(Modifier.size(48.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 🔹 Teks persetujuan
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Text(
                            "Saya telah membaca dan menyetujui",
                            fontSize = 13.5.sp,
                            color = Color(0xFF252C32)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Syarat &",
                            fontSize = 13.5.sp,
                            color = Color(0xFF003FFC),
                            modifier = Modifier.clickable(onClick = onSyaratClick)
                        )
                    }
                    Row {
                        Text(
                            "Ketentuan yang berlaku",
                            fontSize = 13.5.sp,
                            color = Color(0xFF003FFC),
                            modifier = Modifier.clickable(onClick = onSyaratClick)
                        )
                        Text(
                            ".",
                            fontSize = 13.5.sp,
                            color = Color(0xFF252C32)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // 🔹 Tombol Lanjut
                Button(
                    onClick = onLanjut,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text(
                        "Lanjut",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Langkah 3 dari 4",
                fontSize = 11.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Ceritakan Lebih Banyak Tentang Dirimu",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Isilah informasi berikut agar kami bisa mempersonalisasi layanan deposito untukmu.",
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewLangkahScreen() {
    LangkahScreen()
}

package org.kmp.simfan.screen.bukarekening

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Langkah2PanduanScreen(
    onBack: () -> Unit = {},
    onScanWajah: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Panduan Verifikasi Wajah",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onScanWajah,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003FFC))
                ) {
                    Text(
                        "Scan Wajah",
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
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // Header langkah
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF4F4F4))
                    .padding(16.dp)
            ) {
                Text(
                    "Langkah 2 dari 4",
                    fontSize = 11.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE0E7FF))
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Scan Wajah",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Lakukan pemindaian wajah untuk verifikasi identitas anda.",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            // Konten utama
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                // Label Tips
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF003FFC))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.lock),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("Tips", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(16.dp))

                // Gambar wajah
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.foto3),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(16.dp))
                    Image(
                        painter = painterResource(Res.drawable.foto4),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    "Panduan scan wajah:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Daftar poin tips
                val tips = listOf(
                    "Pastikan pencahayaan cukup\nGunakan ruangan yang terang, tetapi hindari cahaya langsung dari belakang.",
                    "Hadapkan wajah ke kamera\nPosisikan wajah di tengah layar dan pastikan menghadap lurus ke depan.",
                    "Jangan gunakan masker atau kacamata hitam\nWajah harus terlihat jelas tanpa penutup.",
                    "Jangan bergerak terlalu cepat\nTetap diam sejenak hingga proses pemindaian selesai.",
                    "Pastikan hanya satu wajah yang terlihat\nHindari orang lain atau objek yang menghalangi wajah Anda."
                )
                tips.forEach { text ->
                    Row(modifier = Modifier.padding(bottom = 8.dp)) {
                        Text("•", color = Color.Black)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text,
                            fontSize = 12.sp,
                            color = Color(0xFF252C32)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLangkah2PanduanScreen() {
    Langkah2PanduanScreen()
}

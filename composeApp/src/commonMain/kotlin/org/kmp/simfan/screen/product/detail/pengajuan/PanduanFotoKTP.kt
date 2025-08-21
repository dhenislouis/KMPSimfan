package org.kmp.simfan.screen.product.detail.pengajuan

import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanduanFotoKTPScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        // 🔹 AppBar
        TopAppBar(
            title = { Text("Panduan Foto KTP", fontWeight = FontWeight.SemiBold) },
            navigationIcon = {
                IconButton(onClick = { /* TODO: Back */ }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        // 🔹 Konten scroll
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(16.dp)
        ) {
            // ✅ Gambar Benar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF8F8F8))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(Res.drawable.ktp_benar),
                    contentDescription = "KTP Benar",
                    modifier = Modifier
                        .width(260.dp)
                        .height(160.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.close),
                            contentDescription = "Check",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text("Benar", fontSize = 17.sp, color = Color.Black)
                }
            }

            Spacer(Modifier.height(20.dp))

            // ✅ Gambar Salah
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SalahCard(
                    image = Res.drawable.ktp_terpotong,
                    label = "Terpotong"
                )
                SalahCard(
                    image = Res.drawable.ktp_buram,
                    label = "Buram"
                )
                SalahCard(
                    image = Res.drawable.ktp_kena_flash,
                    label = "Kena Flash"
                )
            }

            Spacer(Modifier.height(24.dp))

            // ✅ Syarat Foto
            Text("Syarat Foto KTP:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            SyaratItem("1. Gunakan e-KTP asli (bukan fotokopi atau hasil scan)")
            SyaratItem("2. Ambil foto secara horizontal, pastikan e-KTP tidak terpotong dan terlihat jelas")
            SyaratItem("3. Pastikan foto e-KTP tidak terkena bayangan atau cahaya flash")
        }

        // 🔹 Tombol bawah
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /* TODO: Ambil foto */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF023FFC))
            ) {
                Text("Lanjut Buka Rekening", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun SalahCard(image: org.jetbrains.compose.resources.DrawableResource, label: String) {
    Column(
        modifier = Modifier
            .width(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF8F8F8))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = label,
            modifier = Modifier
                .width(99.dp)
                .height(55.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.close),
                contentDescription = "X",
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(label, fontSize = 13.sp, color = Color.Black)
    }
}

@Composable
fun SyaratItem(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        color = Color(0xFF252C32),
        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
    )
}

@Preview()
@Composable
fun PreviewPanduanFotoKTP() {
    PanduanFotoKTPScreen()
}

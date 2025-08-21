package org.kmp.simfan.screen.product.detail.pengajuan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.*

// ✅ Reusable Item untuk Fitur
@Composable
fun FeatureItem(
    icon: DrawableResource,
    title: String,
    desc: List<List<String>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier.size(63.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF252C32)
            )
            desc.forEach { line ->
                Row {
                    line.forEach { word ->
                        Text(
                            text = word,
                            fontSize = 13.sp,
                            color = if (word.contains("%") || word.contains("Rp") || word.contains("pengguna") || word.contains("hadiah"))
                                Color(0xFFF89227) else Color(0xFF252C32),
                            fontWeight = if (word.contains("%") || word.contains("Rp") || word.contains("pengguna") || word.contains("hadiah"))
                                FontWeight.SemiBold else FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengajuanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        // 🔹 AppBar
        TopAppBar(
            title = { Text("Pengajuan Deposito", fontWeight = FontWeight.SemiBold) },
            navigationIcon = {
                IconButton(onClick = { /* TODO: back */ }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        // 🔹 Konten Scrollable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF4F4F4))
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Ajukan Deposito\ndalam 3 Menit,\nSiapkan KTP Anda",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF252C32)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Informasi pribadi Anda dienkripsi\ndan digunakan hanya untuk\nverifikasi",
                        fontSize = 12.sp,
                        color = Color(0xFF252C32)
                    )
                }
                Image(
                    painter = painterResource(Res.drawable.ktp_pengajuan_deposito),
                    contentDescription = "KTP",
                    modifier = Modifier.size(width = 145.dp, height = 125.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // 🔹 Fitur Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                FeatureItem(
                    icon = Res.drawable.fitur1,
                    title = "Lebih banyak bonus",
                    desc = listOf(
                        listOf("Dapatkan bonus", "pengguna baru", "dan", "hadiah"),
                        listOf("referral hingga", "Rp363 ribu ~ 3,6 juta")
                    )
                )
                FeatureItem(
                    icon = Res.drawable.fitur2,
                    title = "Tanpa biaya tersembunyi",
                    desc = listOf(
                        listOf("Transfer gratis, admin fee juga", "Rp0 –", "jadi makin"),
                        listOf("tenang")
                    )
                )
                FeatureItem(
                    icon = Res.drawable.fitur3,
                    title = "Bunga Menarik",
                    desc = listOf(
                        listOf("Raih bunga hingga", "8%", "per tahun untuk"),
                        listOf("penempatan deposito Anda")
                    )
                )
            }

            // 🔹 Persyaratan
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF4F4F4))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Persyaratan:\nHarus warga negara Indonesia dengan KTP yang valid dan berusia 17 tahun ke atas",
                    fontSize = 14.sp,
                    color = Color(0xFF444444)
                )
                Spacer(modifier = Modifier.height(23.dp))
                Image(
                    painter = painterResource(Res.drawable.logo_simfan),
                    contentDescription = "Logo Simfan",
                    modifier = Modifier.height(32.dp)
                )
            }
        }

        // 🔹 Bottom Button
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Saya telah membaca dan menyetujui Syarat & Ketentuan Pengajuan Deposito SimFan dan Ringkasan Produk dan Layanan Deposito",
                fontSize = 13.sp,
                color = Color(0xFF252C32)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                onClick = { /* TODO: aksi lanjut */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF023FFC))
            ) {
                Text("Lanjut", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview()
@Composable
fun PreviewPengajuanScreen() {
    PengajuanScreen()
}

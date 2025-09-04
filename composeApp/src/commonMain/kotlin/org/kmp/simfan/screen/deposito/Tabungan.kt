package org.kmp.simfan.screen.deposito

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.navigation.BottomBar
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

// Warna mock sesuai desain
private val BgSecondary = Color(0xFFF1F2F6)
private val TabSelected = Color(0xFF003FFC)
private val TabUnselected = Color(0xFF6B6B6B)
private val GradientTop = Color(0xFF678CFE)
private val GradientBottom = Color(0xFF003FFC)
private val EstimasiGreen = Color(0xFF22C55E)
private val BannerKrem = Color(0xFFFFF7E0)
private val Secondary = Color(0xFFF59E0B)

@Preview
@Composable
fun SimfankuTabunganScreen(
    navController: NavController,
    currentRoute: Routes?,
    onScreenDeposito: () -> Unit = {},
    onScreenTabungan: () -> Unit = {},
    onDetailTabunganSimfanku: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { navController.navigate(it) }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgSecondary)
        ) {
            // ===== APPBAR =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Deposito",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            // Tombol sejajar di bawah TopBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BgSecondary)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tombol outlined
                OutlinedButton(
                    onClick = onScreenDeposito,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Button1),
                    border = BorderStroke(1.5.dp, Button1),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Deposito",
                        fontSize = 12.sp,
                        color = Button1
                    )
                }

                // Tombol filled
                Button(
                    onClick = onScreenTabungan,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Tabungan",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }

            // ===== SCROLL =====
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                // Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    DepositoTab("Semua", selected = true)
                    DepositoTab("Proses")
                    DepositoTab("Aktif")
                    DepositoTab("Lunas", last = true)
                }

                Spacer(Modifier.height(16.dp))

                // Kartu Total Deposito Aktif
                TotalDepositoCard()

                Spacer(Modifier.height(16.dp))

                // Kartu detail produk
                SimfankuTabunganDetailCard{onDetailTabunganSimfanku()}
            }
        }
    }

}

@Composable
private fun DepositoTab(text: String, selected: Boolean = false, last: Boolean = false) {
    val bg = if (selected) Button1 else Color.Transparent
    val fg = if (selected) Color.White else TabUnselected
    Box(
        modifier = Modifier
            .padding(end = if (last) 0.dp else 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(bg)
            .border(1.dp, TabUnselected.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text, color = fg, fontSize = 13.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
private fun TotalDepositoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(158.dp)
                .fillMaxWidth()
        ) {
            // Layer 1: Gradient background (135°)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xB8A1BAE0), // startColor dengan alpha
                                Color(0xFF4A68FF), // centerColor
                                Color(0xFF4A68FF)  // endColor
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(1000f, 1000f) // arah 135 derajat
                        )
                    )
            )

            // Layer 2: Gambar dekoratif memenuhi tinggi card
            Image(
                painter = painterResource(Res.drawable.bg_card_gambar),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillHeight
            )

            // Layer 3: Konten teks
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Total deposito Aktif", fontSize = 14.sp, color = Color.White)
                Text(
                    "Rp1.509.000",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.9f))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Bunga saat ini", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Spacer(Modifier.weight(1f))
                    Text("↑ Rp250.000", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TabSelected)
                }
            }
        }
    }
}



@Composable
fun SimfankuTabunganDetailCard(
    onDetailTabunganSimfanku: () -> Unit = {}
) {
    Card(
        onClick = onDetailTabunganSimfanku,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            // Gambar + info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.simfan_websuite),
                    contentDescription = null,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    Modifier
                        .padding(start = 12.dp)
                        .weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Simfan Websuite", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                        Spacer(Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .background(Secondary)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text("Proses", fontSize = 10.sp, color = Color.White)
                        }
                    }
                    Row {
                        Text("6 Bulan - Estimasi Aktif", fontSize = 11.sp, color = Color(0xFF999999))
                        Spacer(Modifier.width(4.dp))
                        Text("20 Juli 2025", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF6B7280))
                    }
                }
            }

            // Penempatan & Estimasi
            Row {
                Text("Penempatan", fontSize = 11.sp, color = Color(0xFF22242F), modifier = Modifier.weight(1f))
                Text("Est. Bagi Hasil", fontSize = 11.sp, color = Color(0xFF22242F))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    "Rp10.000.000",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF22242F),
                    modifier = Modifier.weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_upward),
                        contentDescription = null,
                        tint = EstimasiGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text("Rp200.000", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EstimasiGreen)
                }
            }

            // Banner krem
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp) // lebih tipis
                    .clip(RoundedCornerShape(8.dp))
                    .background(BannerKrem)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_time),
                    contentDescription = null,
                    tint = Secondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Tanda tangan Dokumen", fontSize = 13.sp, color = Color.Black, modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(Res.drawable.ic_more),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun DepositoScreenPreview() {
//    DepositoScreen()
//}
package org.kmp.simfan.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.navigation.BottomBar
import org.kmp.simfan.extension.toRupiah
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*
import androidx.compose.foundation.lazy.grid.items

@Composable
fun HomeScreen(
    navController: NavController,
    currentRoute: Routes?,
    onScreenNotification: () -> Unit,
    onScreenPromo: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { navController.navigate(it) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AppBarSection(
                    onNotificationClick = onScreenNotification,
                    onHelpClick = { /* TODO: Arahkan ke Help */ }
                )
            }
            item { ProfileHeader() }
            item { SaldoCard() }
            item { MenuGrid() }
            item { SimulasiDeposito() }
            item { PromoSection(
                onPromoClick = onScreenPromo
            ) }
            item { ProdukRekomendasi() }
            item { DepositoAktif() }
        }
    }
}



@Composable
fun AppBarSection(
    onNotificationClick: () -> Unit,
    onHelpClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo
        Image(
            painter = painterResource(Res.drawable.simfan_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(78.dp)
                .height(29.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Notifikasi
        IconButton(
            onClick = onNotificationClick,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.notification),
                contentDescription = "Notif",
                tint = Color(0xFF003FFC),
                modifier = Modifier
                    .background(Color(0xFFEFF3FF), CircleShape)
                    .padding(6.dp)
                    .fillMaxSize()
            )
        }

        // Help
        IconButton(
            onClick = onHelpClick,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_headset),
                contentDescription = "Help",
                tint = Color(0xFF003FFC),
                modifier = Modifier
                    .background(Color(0xFFEFF3FF), CircleShape)
                    .padding(6.dp)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFFEEF2FF), Color(0xFFEFF6FF))
                )
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ayu_cantika),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Good Morning!", fontSize = 12.sp, color = Color.Gray)
            Text("Cahaya Ayu Cantika", fontSize = 14.sp, color = Color.Black)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("Location", fontSize = 10.sp, color = Color.Gray)
            Text("Jakarta", fontSize = 12.sp, color = Color.Black)
        }
    }
}

@Composable
fun SaldoCard() {
    val infiniteTransition = rememberInfiniteTransition(label = "circleAnim")

    val alpha1 by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha1"
    )

    val offsetX by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetX"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF4A68FF),
                            Color(0xFF3266F6)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    )
                )
                .padding(
                    top = 20.dp,
                    bottom = 20.dp,
                    start = 20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Total Saldo", fontSize = 12.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            painter = painterResource(Res.drawable.eye_on),
                            contentDescription = "eye",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // 🔄 Riwayat dengan background putih & rounded hanya di kiri
                    Row(
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    bottomStart = 50.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 0.dp
                                )
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_riwayat),
                            contentDescription = "Riwayat",
                            tint = Color(0xFF4A68FF), // biru biar kontras
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Riwayat", fontSize = 12.sp, color = Color(0xFF4A68FF))
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Rp13.094.000",
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Tabungan", fontSize = 12.sp, color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = "go",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Text("Rp13.094.000", fontSize = 14.sp, color = Color.White)
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Deposito", fontSize = 12.sp, color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = "go",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Text("Rp0", fontSize = 14.sp, color = Color.White)
                        Text(
                            "Bunga hingga 6.5%",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }
    }
}

data class MenuItem(
    val icon: DrawableResource,
    val title: String
)

object MenuData {
    val sampleMenu = listOf(
        MenuItem(Res.drawable.ic_tabungan_home, "Tabungan"),
        MenuItem(Res.drawable.ic_e_wallet_home, "E-Wallet"),
        MenuItem(Res.drawable.ic_airgas_home, "Air dan Gas"),
        MenuItem(Res.drawable.ic_listrik_home, "Listrik"),
        MenuItem(Res.drawable.ic_pascabayar_home, "Tagihan Pascabayar"),
        MenuItem(Res.drawable.ic_va_home, "Virtual Account"),
        MenuItem(Res.drawable.ic_pulsa_home, "Pulsa/Paket Data"),
        MenuItem(Res.drawable.ic_lainnya_home, "Lainnya"),
    )
}

@Composable
fun MenuGrid() {
    // ✅ Tambah tinggi biar tidak infinite
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp) // <- tinggi grid fix
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(MenuData.sampleMenu) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(72.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Image(
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SimulasiDeposito() {
    var jumlahText by remember { mutableStateOf("100000000") }
    var hasil by remember { mutableStateOf(emptyList<Triple<String, String, String>>()) }

    val bungaTahunan = 0.0625
    val tenorList = listOf(3, 6, 9, 12)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Judul
        Text(
            text = "Hitung Pertumbuhan\nDeposito kamu!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Input jumlah + tombol hitung
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = jumlahText,
                onValueChange = { jumlahText = it.filter { c -> c.isDigit() } },
                leadingIcon = { Text("Rp", fontWeight = FontWeight.Bold) },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    val jumlah = jumlahText.toLongOrNull() ?: 0L
                    if (jumlah in 100_000_000..2_000_000_000) {
                        hasil = tenorList.map { bulan ->
                            val bunga = jumlah * bungaTahunan * (bulan / 12.0)
                            val result = jumlah + bunga
                            Triple(
                                bulan.toString(),
                                formatRupiah(result), // Bank Lainnya
                                formatRupiah(result)  // FUNDtastic (sementara sama, nanti bisa beda)
                            )
                        }
                    } else {
                        hasil = emptyList()
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                modifier = Modifier.height(56.dp)
            ) {
                Text("Hitung", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        if (hasil.isNotEmpty()) {
            // Header tabel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min) // biar divider ikut tinggi row
                    .background(Color(0xFF007BFF), RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableCell("Tenor\n(Bulan)", weight = 1f, color = Color.White, bold = true)
                TableDivider()
                TableCell("Nilai Akhir Deposito\nBank Lainnya (Gross)", weight = 1.5f, color = Color.White, bold = true)
                TableDivider()
                TableCell("Nilai Akhir Deposito di\nFUNDtastic (Gross)", weight = 1.5f, color = Color.White, bold = true)
            }

            // Isi tabel
            hasil.forEachIndexed { index, row ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min) // biar divider ikut tinggi row
                            .background(if (index % 2 == 0) Color.White else Color(0xFFF0F6FF))
                            .padding(vertical = 14.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TableCell(row.first, weight = 1f)
                        TableDivider()
                        TableCell(row.second, weight = 1.5f)
                        TableDivider()
                        TableCell(row.third, weight = 1.5f, color = Color(0xFF007BFF), bold = true)
                    }
                    if (index != hasil.lastIndex) {
                        Divider(
                            color = Color.White,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Rounded bawah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Color(0xFFF0F6FF), RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            )
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    color: Color = Color.Black,
    bold: Boolean = false
) {
    Text(
        text = text,
        color = color,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .weight(weight)
            .padding(horizontal = 4.dp)
    )
}

@Composable
fun RowScope.TableDivider() {
    Box(
        modifier = Modifier
            .fillMaxHeight() // hanya setinggi row
            .width(1.dp)
            .background(Color.White)
    )
}

fun formatRupiah(value: Double): String {
    val number = value.toLong()
    val formatted = number.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
    return "Rp$formatted"
}

@Composable
fun PromoSection(
    onPromoClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable { onPromoClick() }
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header Promo
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Promo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Icon(
                painter = painterResource(Res.drawable.arrow_forward),
                contentDescription = "Lihat Semua",
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Banner Gambar
        Image(
            painter = painterResource(Res.drawable.banner_promo),
            contentDescription = "Banner Promo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun ProdukRekomendasi() {
    Column(Modifier.padding(16.dp)) {
        Text(
            "Produk Deposito Rekomendasi",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            repeat(3) {
                Card(
                    modifier = Modifier
                        .width(165.dp)
                        .padding(end = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite), // ganti dengan assetmu
                            contentDescription = "Produk",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Simfan WebSuite",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            "Jumlah Deposan",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                        Text(
                            "120 orang",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF003FFC)
                        )

//                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            "Suku Bunga (% p.a)",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                        Text(
                            "6.5% p.a",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFF78208)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DepositoAktif() {
    Column(Modifier.padding(16.dp)) {
        Text(
            "Deposito Aktif",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            repeat(3) { // contoh 3 produk
                Card(
                    modifier = Modifier
                        .width(165.dp)
                        .padding(end = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = "Deposito",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Simfan WebSuite",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            "Tenor 3 bulan",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Red
                        )
                        Text(
                            "Jumlah Penempatan",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                        Text(
                            "Rp100.000.000",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF003FFC)
                        )
                        Text(
                            "Bunga (% p.a)",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                        Text(
                            "6.5% p.a",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFF78208)
                        )
                    }
                }
            }
        }
    }
}

//@Preview()
//@Composable
//fun PreviewHome() {
//    HomeScreen(na)
//}
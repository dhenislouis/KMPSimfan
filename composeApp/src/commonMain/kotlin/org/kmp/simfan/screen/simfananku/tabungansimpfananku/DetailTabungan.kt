package org.kmp.simfan.screen.simfananku.tabungansimpfananku

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.screen.simfananku.depositosimpananku.TrackingStatusDetailDeposito
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

data class TrackingStatusDetailTabungan(
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTabunganSimpanankuScreen(
    navController: NavController? = null,
    currentRoute: Routes? = null,
    onBackClick: () -> Unit = {},
    onLihatDetail: () -> Unit = {},
    onTambahPenempatan: () -> Unit = {},
    onAjukanPencairan: () -> Unit = {},

    statusList: List<TrackingStatusDetailDeposito> = emptyList(),
    currentStep: Int = 0
) {
    var checked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Detail Tabungan",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.size(48.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
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
                    onClick = onTambahPenempatan,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
                ) {
                    Text("Tambah Penempatan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = onAjukanPencairan,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text("Ajukan Pencairan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FAFB))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 18.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(Modifier.fillMaxWidth()) {
                            Column(Modifier.weight(1f)) {
                                Text("Penempatan", fontSize = 12.sp, color = Color(0xFF22242F))
                                Text(
                                    "Rp10.000.000",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF22242F)
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Estimasi Bagi Hasil", fontSize = 12.sp, color = Color(0xFF22242F))
                                Text(
                                    "↑ Rp200.000",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF22C55E)
                                )
                            }
                        }

                        Divider(Modifier.padding(vertical = 14.dp), color = Color(0xFFE0E0E0))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "No. Transaksi: 999354-0985-82746",
                                fontSize = 12.sp,
                                color = Color(0xFF22242F),
                                modifier = Modifier.weight(1f)
                            )
                            Row(
                                modifier = Modifier
                                    .background(Color(0xFF003FFC), RoundedCornerShape(50))
                                    .padding(horizontal = 8.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.label_deposito),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text("E-Deposito", fontSize = 9.sp, color = Color.White)
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEFF4FF), RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.aro),
                                contentDescription = null,
                                tint = Color(0xFF003FFC),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Automatic Roll Over (ARO)",
                            fontSize = 10.sp,
                            color = Color(0xFF003FFC)
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        // Header
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Text("Status Tabungan", fontSize = 13.sp, color = Color.Black)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Aktif",
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .background(Color(0xFF22C55E), RoundedCornerShape(24.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                "Lihat Detail",
                                fontSize = 12.sp,
                                color = Color(0xFF003FFC),
                                modifier = Modifier.clickable { onLihatDetail() })
                        }

                        // Info status
                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_info),
                                contentDescription = null,
                                tint = Color(0xFFF89227),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                "Pengajuan sedang dalam proses",
                                fontSize = 11.sp,
                                color = Color(0xFF999999)
                            )
                        }

                        // Timeline
                        Column(Modifier.padding(top = 12.dp)) {
                            statusList.forEachIndexed { index, status ->
                                Row {
                                    // Left indicator
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(end = 12.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(16.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    when {
                                                        index == currentStep -> Color(0xFF003FFC)   // aktif
                                                        index < currentStep -> Color(0xFF22C55E)   // selesai
                                                        else -> Color(0xFFCCCCCC)                  // belum
                                                    }
                                                )
                                        )
                                        if (index != statusList.lastIndex) {
                                            Box(
                                                Modifier
                                                    .width(1.dp)
                                                    .height(68.dp)
                                                    .background(Color(0xFFCCCCCC))
                                            )
                                        }
                                    }

                                    // Right content
                                    Column(modifier = Modifier.padding(bottom = 12.dp)) {
                                        if (index == currentStep) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(Color(0xFFEFF4FF), RoundedCornerShape(8.dp))
                                                    .padding(12.dp)
                                            ) {
                                                Column {
                                                    Text(
                                                        status.title,
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = Color(0xFF003FFC)
                                                    )
                                                    Text(
                                                        status.description,
                                                        fontSize = 10.sp,
                                                        color = Color(0xFF22242F)
                                                    )
                                                }
                                            }
                                        } else {
                                            Column {
                                                Text(
                                                    status.title,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Medium,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    status.description,
                                                    fontSize = 10.sp,
                                                    color = Color(0xFF22242F)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                var expanded by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(1.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    val rotation by androidx.compose.animation.core.animateFloatAsState(
                        if (expanded) 270f else 90f, label = "arrow-rotate"
                    )

                    Column {
                        // Header (collapsed/expanded toggle)
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Thumbnail
                            Image(
                                painter = painterResource(Res.drawable.simfan_websuite),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )
                            Spacer(Modifier.width(12.dp))

                            // Title + subtitle
                            Column(Modifier.weight(1f)) {
                                Text("Simfan WebSuite", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF22242F))
                                Text("DKI Jakarta", fontSize = 10.sp, color = Color(0xFF9AA0A6))
                            }

                            // Chevron (animate rotate)
                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = null,
                                tint = Color(0xFF22242F),
                                modifier = Modifier
                                    .size(18.dp)
                                    .graphicsLayer { rotationZ = rotation }
                            )
                        }

                        if (expanded) {
                            Divider(color = Color(0xFFE0E0E0))

                            Column(Modifier.padding(16.dp)) {
                                @Composable
                                fun DetailRow(label: String, value: String, bold: Boolean = false, blue: Boolean = false) {
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            label,
                                            fontSize = 12.sp,
                                            color = Color(0xFF9AA0A6),
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            value,
                                            fontSize = 12.sp,
                                            fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal,
                                            color = if (blue) Color(0xFF003FFC) else Color(0xFF22242F),
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Right
                                        )
                                    }
                                }

                                DetailRow("Lokasi", "Jakarta Selatan", bold = true)
                                DetailRow("Kode Produk / UID", "BPR-250724–105678–0002", blue = true)
                                DetailRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar", bold = true)

                                Divider(Modifier.padding(vertical = 12.dp), color = Color(0xFFE0E0E0))

                                DetailRow("Minimum Penempatan", "Rp5.000.000", bold = true)
                                DetailRow("Bunga", "6.25%")
                                DetailRow("Tenor", "12 Bulan", bold = true)
                                DetailRow("Tipe Pembayaran Bunga", "Dibayar Setiap Bulan", bold = true)
                                DetailRow("Tipe Dokumen Tabungan", "Tabungan Fisik", bold = true)
                            }
                        }
                    }
                }


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5FF))
                ) {
                    Column {
                        Column(Modifier.padding(12.dp)) {
                            Row(Modifier.fillMaxWidth()) {
                                Text(
                                    "ARO (Automatic Roll Over)",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    "Aktif",
                                    fontSize = 10.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .background(Color(0xFFF89227), RoundedCornerShape(50))
                                        .padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "ARO adalah fitur otomatis untuk memperpanjang tabungan dengan nominal dan tenor yang sama.",
                                fontSize = 12.sp,
                                color = Color(0xFF999999)
                            )
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFDEE9FF))
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_info),
                                contentDescription = null,
                                tint = Color(0xFFF89227),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Perubahan ARO hanya dapat dilakukan ketika status tabungan Kamu telah aktif.",
                                fontSize = 12.sp,
                                color = Color(0xFF22242F)
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column {
                        Column(Modifier.padding(16.dp)) {
                            Text("Akun Bank", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Text(
                                "Pokok dan bunga akan di transfer ke akun ini.",
                                fontSize = 12.sp,
                                color = Color(0xFF999999)
                            )
                        }
                        Divider(color = Color(0xFFDADADA))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.bca_logo),
                                contentDescription = null,
                                modifier = Modifier.size(width = 59.dp, height = 33.dp)
                            )
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text("Bank BCA", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text("726347818910", fontSize = 12.sp, color = Color(0xFF999999))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TrackingDetailTabungan() {
    val trackingDataDetailTabungan = listOf(
        TrackingStatusDetailTabungan("Pengiriman Billyet", "Tandatangani dokumen untuk persetujuan BPR"),
        TrackingStatusDetailTabungan("Penyetoran Dana", "Tandatangani dokumen untuk persetujuan BPR"),
        TrackingStatusDetailTabungan("Menandatangani Dokumen", "Tandatangani dokumen untuk persetujuan BPR"),
        TrackingStatusDetailTabungan("Menunggu persetujuan BPR", "Tandatangani dokumen untuk persetujuan BPR")
    )
    MaterialTheme {
        Surface(color = Color(0xFFF5F5F5), modifier = Modifier.fillMaxSize()) {
            DetailTabunganSimpanankuScreen(
//                statusList = trackingDataDetailTabungan,
                currentStep = 0
            )
        }
    }
}
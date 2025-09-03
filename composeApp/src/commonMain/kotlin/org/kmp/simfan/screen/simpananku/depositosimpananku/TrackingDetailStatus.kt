package org.kmp.simfan.screen.simpananku.depositosimpananku

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.ic_truck

data class TrackingStatusDetailStatus(
    val title: String,
    val description: String,
    val date: String? = null,
    val isActive: Boolean = false,
    val isDone: Boolean = false
)

@Composable
fun TrackingTimelineDetailStatus(statusList: List<TrackingStatusDetailStatus>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_truck),
                    contentDescription = "Tracking",
                    tint = Color(0xFF2979FF),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Tracking Timeline",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Isi status
            statusList.forEachIndexed { index, status ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    // Garis timeline + indikator
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(
                                    when {
                                        status.isActive -> Color(0xFF2979FF)
                                        status.isDone -> Color(0xFFBDBDBD)
                                        else -> Color(0xFFE0E0E0)
                                    },
                                    shape = RoundedCornerShape(50)
                                )
                        )

                        if (index != statusList.lastIndex) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(90.dp)
                                    .background(Color(0xFFE0E0E0))
                            )
                        }
                    }

                    // Konten status
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .background(Color(0xFFF5F6FA), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = status.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (status.isActive) Color(0xFF2979FF) else Color.Black,
                                fontWeight = if (status.isActive) FontWeight.Bold else FontWeight.SemiBold
                            )
                        )
                        if (status.description.isNotEmpty()) {
                            Text(
                                text = status.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.DarkGray
                            )
                        }
                        status.date?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingDetailStatusScreen(
    navController: NavController,
    currentRoute: Routes?,
    onClose: () -> Unit = {}
) {
    val trackingDataDetailStatus = listOf(
        TrackingStatusDetailStatus(
            title = "Dana Cair ke Rekening",
            description = "Dana berhasil ditransfer ke rekening tujuan",
            date = "20 Agustus 2025, 14:20",
            isActive = true
        ),
        TrackingStatusDetailStatus(
            title = "Dana Diproses",
            description = "Proses pencairan sedang dilakukan",
            date = "22 Agustus 2025, 13:30"
        ),
        TrackingStatusDetailStatus(
            title = "Pending",
            description = "Pengajuan bilyet diterima bank",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Pending",
            description = "Pengiriman Bilyet Dilakukan dengan No. Resi xxxxxxxx",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Permintaan Diterima",
            description = "Sistem menerima permintaan penarikan Dana, Segera lakukan pengembalian Bilyet",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Jatuh Tempo",
            description = "Konfirmasi apakah dana ingin dicairkan",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Bilyet Diterima Nasabah",
            description = "20 Agustus 2025, 14:20",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Proses Pengiriman Bilyet",
            description = "Permintaan pencairan e-bilyet diterima.",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Penempatan disetuji BPR",
            description = "Bank sedang memproses pencairan dana Anda.",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Penyetoran Dana",
            description = "Bank sedang memproses pencairan dana Anda.",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusDetailStatus(
            title = "Perjanjian Telah Ditandatangani",
            description = "Bank sedang memproses pencairan dana Anda.",
            date = "20 Agustus 2025, 14:20"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Detail Status",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onClose,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text(
                        "Tutup",
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
                .padding(innerPadding)
                .background(Color(0xFFF4F4F4))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TrackingTimelineDetailStatus(statusList = trackingDataDetailStatus)
        }
    }
}

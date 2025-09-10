package org.kmp.simfan.screen.simpananku.depositosimpananku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
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
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.ic_truck

data class TrackingStatusBilyetFisik(
    val title: String,
    val description: String,
    val date: String? = null,
    val isActive: Boolean = false,
    val isDone: Boolean = false
)

@Composable
fun TrackingTimelineBilyetFisik(statusList: List<TrackingStatusBilyetFisik>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_truck),
                contentDescription = "Tracking",
                tint = Color(0xFF2979FF), // biru
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Tracking Timeline",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

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
                    // Bulatan status
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                when {
                                    status.isActive -> Color(0xFF2979FF) // biru aktif
                                    status.isDone -> Color(0xFFBDBDBD)  // abu selesai
                                    else -> Color(0xFFE0E0E0)          // abu default
                                },
                                shape = RoundedCornerShape(50)
                            )
                    )

                    // Garis penghubung
                    if (index != statusList.lastIndex) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(64.dp)
                                .background(Color(0xFFE0E0E0))
                        )
                    }
                }

                // Konten status
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = status.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (status.isActive) Color(0xFF2979FF) else Color.Black,
                            fontWeight = if (status.isActive) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                    Text(
                        text = status.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingBilyetFisikScreen(
    navController: NavController,
    currentRoute: Routes?,
    onClose: () -> Unit = {}
) {
    val trackingDataBilyetFisik = listOf(
        TrackingStatusBilyetFisik(
            title = "Selesai",
            description = "Menunggu konfirmasi penerimaan",
            isActive = true
        ),
        TrackingStatusBilyetFisik(
            title = "Bilyet tiba di Kota Tujuan",
            description = "Kurir sedang mengantar ke Lokasi Nasabah"
        ),
        TrackingStatusBilyetFisik(
            title = "Bilyet Sampai di DC Cakung",
            description = "Paket sedang disortir"
        ),
        TrackingStatusBilyetFisik(
            title = "Dikirim",
            description = "Kurir mengirimkan bilyet\nResi: JNE123456",
            date = "22 Agustus 2025, 13:30"
        ),
        TrackingStatusBilyetFisik(
            title = "Proses",
            description = "Bilyet sedang dicetak",
            date = "20 Agustus 2025, 14:20"
        ),
        TrackingStatusBilyetFisik(
            title = "Pending",
            description = "Pengajuan bilyet diterima bank",
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
                            "Tracking Billyet",
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TrackingTimelineBilyetFisik(statusList = trackingDataBilyetFisik)
        }
    }
}

//@Preview
//@Composable
//fun PreviewTrackingBilyetFisikScreen() {
//    MaterialTheme {
//        Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//            TrackingBilyetFisikScreen()
//        }
//    }
//}

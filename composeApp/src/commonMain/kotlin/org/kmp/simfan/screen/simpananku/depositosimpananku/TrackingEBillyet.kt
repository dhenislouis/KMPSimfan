package org.kmp.simfan.screen.simpananku.depositosimpananku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

data class TrackingStatusEBillyet(
    val title: String,
    val description: String,
    val date: String? = null,
    val isActive: Boolean = false,
    val isDone: Boolean = false
)

@Composable
fun TrackingTimelineEBillyet(statusList: List<TrackingStatusEBillyet>) {
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
                imageVector = Icons.Default.LocalShipping,
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
                                .height(48.dp)
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

@Composable
fun TrackingEBillyetScreen() {
    val trackingDataEBillyet = listOf(
        TrackingStatusEBillyet(
            title = "Selesai",
            description = "E-Billyet Deposito #xxxxxxx telah dikirim ke Email user@gmail.com",
            date = "20 Agustus 2025, 14:20",
            isActive = true
        ),
        TrackingStatusEBillyet(
            title = "Proses",
            description = "",
            date = "20 Agustus 2025, 14:20"
        )
    )

    TrackingTimelineEBillyet(statusList = trackingDataEBillyet)
}

@Preview
@Composable
fun PreviewTrackingEBillyetScreen() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TrackingEBillyetScreen()
        }
    }
}

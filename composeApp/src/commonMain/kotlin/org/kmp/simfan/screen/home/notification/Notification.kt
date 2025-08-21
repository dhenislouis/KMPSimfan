package org.kmp.simfan.screen.home.notification

import org.jetbrains.compose.ui.tooling.preview.Preview
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.close
import simfan.composeapp.generated.resources.notification

@Composable
fun NotificationScreen(
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA)) // bg_secondary
    ) {
        // 🔹 AppBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCloseClick) {
                Icon(
                    painter = painterResource(Res.drawable.close),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Notifikasi",
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        // 🔹 List Notifikasi (scrollable)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            NotificationCard(
                title = "Penarikan deposito berhasil.",
                message = "Dana dari penempatanmu telah berhasil dicairkan sesuai tujuan yang kamu pilih."
            )

            NotificationCard(
                title = "Top up berhasil.",
                message = "Dana top up sebesar Rp1.000.000 berhasil masuk ke akunmu."
            )

            NotificationCard(
                title = "Promo spesial 🎉",
                message = "Dapatkan bunga lebih tinggi untuk penempatan deposito bulan ini."
            )
        }
    }
}

@Composable
fun NotificationCard(
    title: String,
    message: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ikon bulat
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFEFF3FF), shape = CircleShape), // bg_notifikasi_icon
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.notification),
                    contentDescription = "Notifikasi",
                    tint = Color(0xFF003FFC),
                    modifier = Modifier.size(24.dp)
                )
            }

            // Konten teks
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color(0xFF27364E)
                )
                Text(
                    text = message,
                    fontSize = 14.sp,
                    color = Color(0xFF27364E),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Preview()
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(onCloseClick = {})
}

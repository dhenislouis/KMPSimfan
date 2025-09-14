package org.kmp.simfan.screen.home.notification

import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.close
import simfan.composeapp.generated.resources.notification

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavController,
    currentRoute: Routes?,
    onCloseClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Notifikasi",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            painter = painterResource(Res.drawable.close),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                actions = {
                    Spacer(Modifier.size(48.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F6FA)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                NotificationCard(
                    title = "Penarikan deposito berhasil.",
                    message = "Dana dari penempatanmu telah berhasil dicairkan sesuai tujuan yang kamu pilih."
                )
            }
            item {
                NotificationCard(
                    title = "Top up berhasil.",
                    message = "Dana top up sebesar Rp1.000.000 berhasil masuk ke akunmu."
                )
            }
            item {
                NotificationCard(
                    title = "Promo spesial 🎉",
                    message = "Dapatkan bunga lebih tinggi untuk penempatan deposito bulan ini."
                )
            }
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
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFEFF3FF), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.notification),
                    contentDescription = "Notifikasi",
                    tint = Color(0xFF003FFC),
                    modifier = Modifier.size(24.dp)
                )
            }

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

//@Preview
//@Composable
//fun NotificationScreenPreview() {
//    NotificationScreen()
//}
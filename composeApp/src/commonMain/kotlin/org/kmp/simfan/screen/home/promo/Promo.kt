package org.kmp.simfan.screen.home.promo

import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.*

@Composable
fun PromoScreen(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit
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
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Promo",
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        // 🔹 Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8ECF8)) // bg_promo_header
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.logo_simfan),
                contentDescription = "SimFan Logo",
                modifier = Modifier
                    .width(85.dp)
                    .height(28.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(Res.drawable.menu),
                    contentDescription = "Menu Icon",
                    tint = Color(0xFF252C32),
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        // 🔹 Konten scrollable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PromoCard(
                title = "Kejutan Tanggal Kembar 7.7! Raih Cashback s/d 750Rb + Voucher Gopay 55Rb!",
                source = "SimFan by SimFan",
                date = "07 July 2025"
            )
        }
    }
}

@Composable
fun PromoCard(
    title: String,
    source: String,
    date: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Banner
            Image(
                painter = painterResource(Res.drawable.banner_promo),
                contentDescription = "Promo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontSize = 13.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = source,
                fontSize = 12.sp,
                color = Color(0xFF6B7280)
            )

            Text(
                text = date,
                fontSize = 12.sp,
                color = Color(0xFF6B7280)
            )
        }
    }
}

@Preview()
@Composable
fun PromoScreenPreview() {
    PromoScreen(onBackClick = {}, onMenuClick = {})
}

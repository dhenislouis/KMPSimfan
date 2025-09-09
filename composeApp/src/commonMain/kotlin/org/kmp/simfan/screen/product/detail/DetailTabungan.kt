package org.kmp.simfan.screen.product.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.components.navbar.CustomAppBar
import org.kmp.simfan.core.BgSecondary
import org.kmp.simfan.core.Primary

import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.simfan_websuite
@Composable
fun DetailTabunganScreen(
    onDetailBprClick: () -> Unit = {},
    onDetailClick: () -> Unit = {},
    onAjukanClick: () -> Unit = {}, // aksi tombol di bottom bar
    navController: NavController,
) {
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(24.dp)
            ) {
                Button(
                    onClick = onAjukanClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Ajukan Penempatan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        },
        topBar = {
            CustomAppBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgSecondary)
                .verticalScroll(rememberScrollState()) // biar konten bisa discroll
                .padding(paddingValues) // biar konten gak ketimpa bottom bar
                .padding(16.dp)
        // padding dalam konten
        ) {


            MinimumPenempatanCard(
                minimumLabel = "Minimum Penempatan",
                nominal = "Rp10.000.000",
                duration = "3 Bulan",
                estimasi = "6%"
            )

            Spacer(modifier = Modifier.height(16.dp))

            DetailBprCard(
                onDetailBprClick = onDetailBprClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    "Product Lainnya",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                AnotherProductCard(
                    title = "Simfan WebSuite",
                    subtitle = "DKI Jakarta - 3 Transaksi",
                    minimumLabel = "Minimum Penempatan",
                    duration = "3 Bulan",
                    nominal = "Rp10.000.000",
                    estimasi = "6%",
                    showStatusBar = true,
                    statusTitle = "Belum dapat menerima transaksi.",
                    statusSubtitle = "Data sedang diperbarui",
                    onDetailClick = onDetailClick,
                    onDetailBprClick = onDetailBprClick,
                )
            }
        }
    }
}


@Composable
fun MinimumPenempatanCard(
    minimumLabel: String,
    duration: String,
    nominal: String,
    estimasi: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFDADADA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Column(Modifier.padding(top = 18.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)) {
                // Minimum Penempatan | Durasi
                Row {
                    Text(minimumLabel, fontSize = 12.sp, color = Color(0xFF22242F), modifier = Modifier.weight(1f))
                    Text(duration, fontSize = 12.sp, color = Color(0xFF22242F), )
                }

                Spacer(Modifier.height(4.dp))

                // Nominal | Estimasi (dengan ikon panah hijau)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        nominal,
                        fontSize = 15.sp,
                        color = Color(0xFF22242F),
                        fontWeight = FontWeight.Bold,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = Res.getUri("files/ic_arrow_up.svg") ,
                            contentDescription = "House",
                            modifier = Modifier.size(15.dp),
                            colorFilter = ColorFilter.tint(org.kmp.simfan.core.PositiveGreen)
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            estimasi,
                            fontSize = 15.sp,
                            color = org.kmp.simfan.core.PositiveGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFE5E7EB)
                )
                Spacer(Modifier.height(12.dp))

                Text("Total 5 Transaksi sejak produk ini dibuat",
                    fontSize = 12.sp, color = Color(0xFF22242F),
                )
            }


    }
}
}


@Composable
fun DetailBprCard(
    title: String = "BPR Simfan",
    subtitle: String = "Bank Perkreditan Rakyat",
    onDetailBprClick: () -> Unit = {},
) {
    Column {
        //app bar
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFDADADA)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {


                // Section 1 - Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onDetailBprClick)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Image(
                                painter = painterResource(Res.drawable.simfan_websuite),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(5.dp)),
                                contentScale = ContentScale.FillWidth
                            )
                            Column(
                                Modifier
                                    .weight(1f)
                                    .padding(start = 12.dp)
                            ) {
                                Text(
                                    title,
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(subtitle, fontSize = 11.sp, color = Color(0xFF999999))
                            }
                            AsyncImage(
                                model = Res.getUri("files/ic_arrow_left.svg") ,
                                contentDescription = "Arrow",
                                modifier = Modifier.size(16.dp),
                                colorFilter = ColorFilter.tint(Color.Black)
                            )
                        }
                        Spacer(Modifier.height(14.dp))
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color(0xFFE5E7EB)
                        )
                    }
                }

                // Section 2 - Informasi umum
                Column(
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    InfoRow("Lokasi", "Jakarta Selatan")
                    InfoRow("Kode Produk / UID", "BPR-250724–105678–0002")
                    InfoRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar")
                }

                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE5E7EB))

                // Section 3 - Informasi lain
                Column(
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    InfoRow("Minimum Pembelian", "Rp5.000.000")
                    InfoRow("Bunga", "2,25%")
                    InfoRow("Tenor", "12 Bulan")
                    InfoRow("Tipe Pembayaran Bunga", "65%")

                }
            }
        }
    }

}



@Composable
fun AnotherProductCard(
    title: String,
    subtitle: String,
    minimumLabel: String,
    duration: String,
    nominal: String,
    estimasi: String,
    showStatusBar: Boolean = false,
    statusTitle: String = "",
    statusSubtitle: String = "",
    onDetailClick: () -> Unit,
    onDetailBprClick: () -> Unit

) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Column(Modifier.padding(top = 18.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)) {
                // Header: gambar, judul, subjudul + label
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onDetailBprClick)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Image(
                                painter = painterResource(Res.drawable.simfan_websuite),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(5.dp)),
                                contentScale = ContentScale.FillWidth
                            )
                            Column(
                                Modifier
                                    .weight(1f)
                                    .padding(start = 12.dp)
                            ) {
                                Text(
                                    title,
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(subtitle, fontSize = 11.sp, color = Color(0xFF999999))
                            }
                            AsyncImage(
                                model = Res.getUri("files/ic_arrow_left.svg"),
                                contentDescription = "Arrow",
                                modifier = Modifier.size(16.dp),
                                colorFilter = ColorFilter.tint(Color.Black)
                            )
                        }
                        Spacer(Modifier.height(14.dp))
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color(0xFFE5E7EB)
                        )
                    }
                }
                Spacer(Modifier.height(14.dp))

                // Minimum Penempatan | Durasi
                Row {
                    Text(
                        minimumLabel,
                        fontSize = 12.sp,
                        color = Color(0xFF22242F),
                        modifier = Modifier.weight(1f)
                    )
                    Text(duration, fontSize = 12.sp, color = Color(0xFF22242F),)
                }

                Spacer(Modifier.height(4.dp))

                // Nominal | Estimasi (dengan ikon panah hijau)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        nominal,
                        fontSize = 15.sp,
                        color = Color(0xFF22242F),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = Res.getUri("files/ic_arrow_up.svg"),
                            contentDescription = "House",
                            modifier = Modifier.size(15.dp),
                            colorFilter = ColorFilter.tint(org.kmp.simfan.core.PositiveGreen)
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            estimasi,
                            fontSize = 15.sp,
                            color = org.kmp.simfan.core.PositiveGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }

        }
    }
}

package org.kmp.simfan.screen.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.Primary
import org.kmp.simfan.core.navigation.BottomBar
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.arrow_upward
import simfan.composeapp.generated.resources.ic_filter
import simfan.composeapp.generated.resources.ic_more
import simfan.composeapp.generated.resources.ic_refresh
import simfan.composeapp.generated.resources.ic_search
import simfan.composeapp.generated.resources.simfan_websuite

private val BgSecondary = Color(0xFFF1F2F6)
private val AppBarBg = Color(0xFFF8F9FE)
private val PrimaryBlue = Color(0xFF678CFE)
private val AroBlue = Color(0xFF003FFC)
private val PositiveGreen = Color(0xFF22C55E)
private val LabelOrange = Color(0xFFFF8A00)
private val StatusBgYellow = Color(0xFFFFF7E0)
private val StatusText = Color(0xFFF59E0B)

@Preview
@Composable
fun ProductTabunganScreen(
    navController: NavController,
    currentRoute: Routes?,
    onFilterClick: () -> Unit = {},
    onDetailClick: () -> Unit = {},
    onDetailBprClick: () -> Unit = {},
//    onSaveClick: () -> Unit = {},
//    onScreenDeposito: () -> Unit = {},
//    onScreenTabungan: () -> Unit = {}
) {
// Daftar produk
    // Cari Produk di bawah tombol sejajar
    Column {
        Column(
            modifier = Modifier
                .background(BgSecondary)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            var search by remember { mutableStateOf("") }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(100.dp),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffFBFBF9))
                        .padding(start = 16.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = Res.getUri("files/ic_magnifying.svg") ,
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(Modifier.width(8.dp))
                    BasicTextField(
                        value = search,
                        onValueChange = { search = it },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        decorationBox = { inner ->
                            if (search.isEmpty()) {
                                Text("Cari produk", color = Color.Gray, fontSize = 14.sp)
                            }
                            inner()
                        }
                    )

                    AsyncImage(
                        model = Res.getUri("files/ic_filter.svg") ,
                        contentDescription = "Filter",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onFilterClick() },

                        )
                }
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            ProductTabunganCard(
                title = "Simfan Websuite",
                subtitle = "DKI Jakarta - 3 Transaksi",
                minimumLabel = "Minimum Penempatan",
                duration = "3 Bulan",
                nominal = "Rp10.000.000",
                estimasi = "6%",
                showBilyet = true,
                showAro = true,
                onDetailClick = onDetailClick,
                onDetailBprClick = onDetailBprClick,

                )

            Spacer(Modifier.height(12.dp))

            ProductTabunganCard(
                title = "Simfan WebSuite",
                subtitle = "DKI Jakarta - 3 Transaksi",
                minimumLabel = "Minimum Penempatan",
                duration = "3 Bulan",
                nominal = "Rp10.000.000",
                estimasi = "6%",
                showBilyet = false,
                showAro = false,
                showStatusBar = true,
                statusTitle = "Belum dapat menerima transaksi.",
                statusSubtitle = "Data sedang diperbarui",
                onDetailClick = onDetailClick,
                onDetailBprClick = onDetailBprClick,

                )
        }
    }

}

@Composable
private fun ProductTabunganCard(
    title: String,
    subtitle: String,
    minimumLabel: String,
    duration: String,
    nominal: String,
    estimasi: String,
    showBilyet: Boolean,
    showAro: Boolean,
    showStatusBar: Boolean = false,
    statusTitle: String = "",
    statusSubtitle: String = "",
    onDetailClick: () -> Unit,
    onDetailBprClick: () -> Unit

) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
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
                Spacer(Modifier.height(14.dp))

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
                        modifier = Modifier.weight(1f)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = Res.getUri("files/ic_arrow_up.svg") ,
                            contentDescription = "House",
                            modifier = Modifier.size(15.dp),
                            colorFilter = ColorFilter.tint(PositiveGreen)
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            estimasi,
                            fontSize = 15.sp,
                            color = PositiveGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(Modifier.height(14.dp))

                Button(
                    onClick = { onDetailClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text("Detail Product", color = Color.White)
                }

                Spacer(Modifier.height(8.dp))
            }

            // Status bar (khusus kartu kedua)
            if (showStatusBar) {
                StatusInfoBar(
                    title = statusTitle,
                    subtitle = statusSubtitle
                )
            }
        }
    }
}

@Composable
private fun PillLabel(
    text: String,
    bg: Color,
    fg: Color,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text, color = fg, fontSize = 10.sp, fontWeight = FontWeight.Normal)
    }
}

@Composable
private fun AroLabel() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color(0x22003FFC))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // lingkaran biru kecil dengan ikon panah white
        Box(
            modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(AroBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.aro),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
        Spacer(Modifier.width(5.dp))
        Text("ARO", fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun StatusInfoBar(
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(StatusBgYellow)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_refresh),
            contentDescription = null,
            tint = StatusText,
            modifier = Modifier.size(24.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(title, color = StatusText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = StatusText, fontSize = 12.sp)
        }
        Icon(
            painter = painterResource(Res.drawable.ic_more),
            contentDescription = null,
            tint = StatusText,
            modifier = Modifier.size(20.dp)
        )
    }
}

//@Preview
//@Composable
//private fun ProductTabunganPreview() {
//    ProductTabunganScreen()
//}
package org.kmp.simfan.screen.product.productdeposito

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.screen.product.productdeposito.InfoRow
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.arrow_forward
import simfan.composeapp.generated.resources.simfan_websuite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductDepositoScreen(
    navController: NavController,
    currentRoute: Routes.DetailProductDeposito?,
    onBackClick: () -> Unit,
    onDetailProdukLainnya: () -> Unit = {},
    onDetailBprClick: () -> Unit = {},
    onAjukanClick: () -> Unit = {}
) {
    var checked by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        InputNominalBottomSheet(
            navController = navController,
            currentRoute = Routes.BottomSheetPengajuanProductDeposito,
            onDismiss = { showBottomSheet = false },
            onSave = { nominal ->
                // TODO: proses nominal di sini
                showBottomSheet = false
                navController.navigate(Routes.AjukanPenempatanProductDeposito)
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            androidx.compose.material3.CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detail Produk",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
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
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        showBottomSheet = true
                        onAjukanClick()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text(
                        "Ajukan Penempatan",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F4F6))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            MinimumPenempatanCard(
                minimumLabel = "Minimum Penempatan",
                nominal = "Rp10.000.000",
                duration = "3 Bulan",
                estimasi = "6% p.a"
            )

            Spacer(modifier = Modifier.height(12.dp))

            AROCard(
                checked = checked,
                onCheckedChange = { checked = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            DetailBPRCard(
                onDetailBprClick = onDetailBprClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Produk Lainnya",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            AnotherProductCard(
                title = "Simfan Websuite",
                subtitle = "DKI Jakarta - 3 Transaksi",
                minimumLabel = "Minimum Penempatan",
                duration = "3 Bulan",
                nominal = "Rp10.000.000",
                estimasi = "6%",
                onDetailClick = onDetailProdukLainnya
            )
        }
    }
}

@Composable
fun MinimumPenempatanCard(
    minimumLabel: String,
    nominal: String,
    duration: String,
    estimasi: String
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
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
fun AROCard(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF003FFC).copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .padding(vertical = 6.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.aro),
                contentDescription = null,
                tint = Color(0xFF003FFC),
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "Automatic Roll Over (ARO)",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color(0xFF003FFC),
            modifier = Modifier.padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF003FFC),
                uncheckedColor = Color.Gray
            )
        )
    }
}

@Composable
fun DetailBPRCard(
    onDetailBprClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFDADADA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header yang dapat diklik untuk toggle dropdown
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                verticalAlignment = Alignment.CenterVertically
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
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text("BPR Sejahtera", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text(
                        "DKI Jakarta - 3 Transaksi",
                        fontSize = 11.sp,
                        color = Color(0xFF999999)
                    )
                }
                // Ikon yang berubah berdasarkan state expanded
                Icon(
                    painter = painterResource(
                        if (expanded) Res.drawable.arrow_back else Res.drawable.arrow_forward
                    ),
                    contentDescription = if (expanded) "Tutup detail" else "Buka detail",
                    tint = Color.Gray,
                    modifier = Modifier.rotate(if (expanded) 90f else 0f)
                )
            }

            // Konten detail yang muncul saat card terbuka
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0xFFE5E7EB),
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                    InfoRow("Lokasi", "Jakarta Selatan")
                    InfoRow("Kode Produk / UID", "BPR-250724–105678–0002")
                    InfoRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar")
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0xFFE5E7EB),
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                    InfoRow("Minimum Penempatan", "Rp5.000.000")
                    InfoRow("Bunga", "6.25%")
                    InfoRow("Tenor", "12 Bulan")
                    InfoRow("Tipe Pembayaran Bunga", "Dibayar Setiap Bulan")
                    InfoRow("Tipe Dokumen Deposito", "Deposito Fisik")
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
    onDetailClick: () -> Unit = {}
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.simfan_websuite),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(title, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text(
                        subtitle,
                        fontSize = 11.sp,
                        color = Color(0xFF999999)
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE5E7EB),
                modifier = Modifier.padding(vertical = 14.dp)
            )
            InfoRow(minimumLabel, duration)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(nominal, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(estimasi, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF22C55E))
            }
        }
    }
}
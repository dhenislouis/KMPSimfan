package org.kmp.simfan.screen.product.productdeposito.productdeposito

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.arrow_forward
import simfan.composeapp.generated.resources.simfan_websuite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengajuanProductDepositoScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit,
    onDetailProdukLainnya: () -> Unit
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
            CenterAlignedTopAppBar(
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
                    onClick = { showBottomSheet = true },
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
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Minimum Penempatan", fontSize = 12.sp, color = Color(0xFF22242F))
                            Text(
                                "Rp10.000.000",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF22242F)
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("3 Bulan", fontSize = 12.sp, color = Color(0xFF22242F))
                            Text(
                                "6% p.a",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF22C55E)
                            )
                        }
                    }
                    DividerLine()
                    Text(
                        text = "Total 5 transaksi sejak produk ini dibuat",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF003FFC).copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .padding(vertical = 10.dp, horizontal = 16.dp),
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
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF003FFC),
                        uncheckedColor = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp))
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
                        Icon(
                            painter = painterResource(Res.drawable.arrow_forward),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                    DividerLine(Modifier.padding(vertical = 15.dp))
                    InfoRow("Lokasi", "Jakarta Selatan")
                    InfoRow("Kode Produk / UID", "BPR-250724–105678–0002")
                    InfoRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar")
                    DividerLine(Modifier.padding(vertical = 15.dp))
                    InfoRow("Minimum Penempatan", "Rp5.000.000")
                    InfoRow("Bunga", "6.25%")
                    InfoRow("Tenor", "12 Bulan")
                    InfoRow("Tipe Pembayaran Bunga", "Dibayar Setiap Bulan")
                    InfoRow("Tipe Dokumen Deposito", "Deposito Fisik")
                }
            }

            Text(
                text = "Produk Lainnya",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                onClick = onDetailProdukLainnya,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                        ) {
                            Text("Simfan Websuite", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Text(
                                "DKI Jakarta - 3 Transaksi",
                                fontSize = 11.sp,
                                color = Color(0xFF999999)
                            )
                        }
                    }
                    DividerLine(Modifier.padding(vertical = 14.dp))
                    InfoRow("Minimum Penempatan", "3 Bulan")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Rp10.000.000", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("6%", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF22C55E))
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 12.sp, color = Color(0xFF22242F))
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF22242F))
    }
}

@Composable
fun DividerLine(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE0E0E0))
    )
}

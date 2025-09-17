package org.kmp.simfan.screen.product.producttabungan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*
import org.kmp.simfan.core.Button1
import org.kmp.simfan.screen.product.productdeposito.InfoRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjukanPenempatanProdukTabunganScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onAjukanClick: () -> Unit = {}
) {
    data class BankAccount(val logo: DrawableResource, val name: String, val number: String)
    val akunBankList = listOf(
        BankAccount(Res.drawable.bca_logo, "Bank BCA", "726347818910"),
        BankAccount(Res.drawable.mandiri_logo, "Bank Mandiri", "9876543210"),
        BankAccount(Res.drawable.bri_logo, "Bank BRI", "1234567890")
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedBank by remember { mutableStateOf(akunBankList[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Ajukan Penempatan",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onAjukanClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text("Ajukan & Tanda Tangan", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FAFB))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(Modifier.fillMaxWidth()) {
                        Column(Modifier.weight(1f)) {
                            Text("Penempatan", fontSize = 12.sp, color = Color(0xFF22242F))
                            Text(
                                "Rp10.000.000",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF22242F)
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Estimasi Bagi Hasil", fontSize = 12.sp, color = Color(0xFF22242F))
                            Text(
                                "↑ Rp200.000",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF22C55E)
                            )
                        }
                    }
                    Divider(Modifier.padding(vertical = 14.dp), color = Color(0xFFE0E0E0))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "No. Transaksi: 999354-0985-82746",
                            fontSize = 12.sp,
                            color = Color(0xFF22242F),
                            modifier = Modifier.weight(1f)
                        )
                        Row(
                            modifier = Modifier
                                .background(Button1, RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.label_deposito),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text("E-Deposito", fontSize = 9.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider(Modifier.padding(vertical = 14.dp), color = Color(0xFFE0E0E0))

            Spacer(modifier = Modifier.height(8.dp))

            DetailBPRCardAjukan()

            Spacer(modifier = Modifier.height(8.dp))

            Divider(Modifier.padding(vertical = 14.dp), color = Color(0xFFE0E0E0))

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    // Header
                    Column(Modifier.padding(16.dp)) {
                        Text("Akun Bank", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text(
                            "Pokok dan bunga akan di transfer ke akun ini.",
                            fontSize = 12.sp,
                            color = Color(0xFF999999)
                        )
                    }

                    Divider(color = Color(0xFFDADADA))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(selectedBank.logo),
                                contentDescription = null,
                                modifier = Modifier.size(width = 59.dp, height = 33.dp)
                            )
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(selectedBank.name, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                Text(selectedBank.number, fontSize = 12.sp, color = Color(0xFF999999))
                            }
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        akunBankList.forEach { bank ->
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(bank.logo),
                                            contentDescription = null,
                                            modifier = Modifier.size(width = 59.dp, height = 33.dp)
                                        )
                                        Spacer(Modifier.width(12.dp))
                                        Column {
                                            Text(bank.name, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                            Text(bank.number, fontSize = 12.sp, color = Color(0xFF999999))
                                        }
                                    }
                                },
                                onClick = {
                                    selectedBank = bank
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailBPRCardAjukan() {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFDADADA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
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
                        .size(40.dp)
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
                Icon(
                    painter = painterResource(
                        if (expanded) Res.drawable.arrow_back else Res.drawable.arrow_forward
                    ),
                    contentDescription = if (expanded) "Tutup detail" else "Buka detail",
                    tint = Color.Gray,
                    modifier = Modifier.rotate(if (expanded) 45f else 0f)
                )
            }

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

//@Preview
//@Composable
//fun PreviewDetailDeposito() {
//    AjukanPenempatanProdukDepositoScreen()
//}

package org.kmp.simfan.screen.simfananku.detail.detailtabungan

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

@Composable
fun DetailTabunganScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onTandaTangan: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
//                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "Detail Deposito",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = {},
                enabled = false,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            ) {}
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
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
                                .background(Color(0xFF003FFC), RoundedCornerShape(50))
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
                            Text("E-Deposito", fontSize = 9.sp, color = Color.White)
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFF4FF), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.aro),
                        contentDescription = null,
                        tint = Color(0xFF003FFC),
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    "Automatic Roll Over (ARO)",
                    fontSize = 10.sp,
                    color = Color(0xFF003FFC)
                )
            }

            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text("Status Deposito", fontSize = 13.sp, color = Color.Black)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Proses",
                            fontSize = 10.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF003FFC), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text("Lihat Detail", fontSize = 10.sp, color = Color(0xFF003FFC))
                    }

                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_info),
                            contentDescription = null,
                            tint = Color(0xFFF89227),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            "Pengajuan sedang dalam proses",
                            fontSize = 11.sp,
                            color = Color(0xFF999999)
                        )
                    }

                    Row(Modifier.padding(top = 12.dp)) {
                        Column(
                            modifier = Modifier
                                .padding(top = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.close),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Box(
                                Modifier
                                    .width(1.dp)
                                    .height(30.dp)
                                    .background(Color(0xFFCCCCCC))
                            )
                            Icon(
                                painter = painterResource(Res.drawable.close),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFEFF4FF), RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Text(
                                        "Menandatangani Dokumen",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF003FFC)
                                    )
                                    Text(
                                        "Tandatangani dokumen untuk persetujuan BPR",
                                        fontSize = 10.sp,
                                        color = Color(0xFF22242F)
                                    )
                                }
                            }
                            Spacer(Modifier.height(12.dp))
                            Text(
                                "Menunggu Persetujuan BPR",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            var expanded by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = null,
                            modifier = Modifier.size(38.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Simfan Websuite", fontSize = 13.sp, color = Color.Black)
                            Text("DKI Jakarta", fontSize = 10.sp, color = Color(0xFF999999))
                        }
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                    if (expanded) {
                        Divider(color = Color(0xFFE0E0E0))
                        Column(Modifier.padding(16.dp)) {
                            Text("Tanggal Pengajuan: Senin, 15 Juli 2025", fontSize = 13.sp)
                            Text("Penempatan Deposito: Rp50.000.000", fontSize = 13.sp)
                            Text("Est. Permulaan Deposito: Rabu, 17 Juli 2025", fontSize = 13.sp)
                            Text("Bagi Hasil Setara: 6,00%", fontSize = 13.sp)
                            Text("Tenor: 6 bulan", fontSize = 13.sp)
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5FF))
            ) {
                Column {
                    Column(Modifier.padding(12.dp)) {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                "ARO (Automatic Roll Over)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                "Aktif",
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .background(Color(0xFFF89227), RoundedCornerShape(50))
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "ARO adalah fitur otomatis untuk memperpanjang deposito dengan nominal dan tenor yang sama.",
                            fontSize = 12.sp,
                            color = Color(0xFF999999)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFDEE9FF))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_info),
                            contentDescription = null,
                            tint = Color(0xFFF89227),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Perubahan ARO hanya dapat dilakukan ketika status deposito Kamu telah aktif.",
                            fontSize = 12.sp,
                            color = Color(0xFF22242F)
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    Column(Modifier.padding(16.dp)) {
                        Text("Akun Bank", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text(
                            "Pokok dan bunga akan di transfer ke akun ini.",
                            fontSize = 12.sp,
                            color = Color(0xFF999999)
                        )
                    }
                    Divider(color = Color(0xFFDADADA))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.bca_logo),
                            contentDescription = null,
                            modifier = Modifier.size(width = 59.dp, height = 33.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("Bank BCA", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Text("726347818910", fontSize = 12.sp, color = Color(0xFF999999))
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* aksi opsi lain */ },
                modifier = Modifier
                    .size(50.dp)
                    .background(Button1, CircleShape)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_more),
                    contentDescription = "Lihat opsi lain",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = onTandaTangan,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Button1)
            ) {
                Text("Tanda Tangan di Sini", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

//@Preview()
//@Composable
//fun PreviewDetailDeposito() {
//    DetailDepositoScreen()
//}

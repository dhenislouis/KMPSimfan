package org.kmp.simfan.screen.product.detail

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.arrow_forward
import simfan.composeapp.generated.resources.simfan_websuite

@Composable
fun DetailScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit,
    onAjukanClick: () -> Unit,
    DetailProdukLainnya: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
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
                text = "Detail Produk",
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
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Minimum Penempatan",
                                fontSize = 12.sp,
                                color = Color(0xFF22242F)
                            )
                            Text(
                                text = "Rp10.000.000",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF22242F)
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "3 Bulan",
                                fontSize = 12.sp,
                                color = Color(0xFF22242F)
                            )
                            Text(
                                text = "6% p.a",
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
                    fontSize = 12.5.sp,
                    color = Color(0xFF003FFC),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = "Produk",
                            modifier = Modifier.size(38.dp)
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                        ) {
                            Text("Simfan Websuite", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Text("DKI Jakarta - 3 Transaksi", fontSize = 11.sp, color = Color(0xFF999999))
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
                onClick = DetailProdukLainnya,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = "Produk",
                            modifier = Modifier.size(38.dp)
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                        ) {
                            Text("Simfan Websuite", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Text("DKI Jakarta - 3 Transaksi", fontSize = 11.sp, color = Color(0xFF999999))
                        }
                    }
                    DividerLine(Modifier.padding(vertical = 14.dp))
                    InfoRow("Minimum Penempatan", "3 Bulan")
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Rp10.000.000", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("6%", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF22C55E))
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Button(
                onClick = onAjukanClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Button1)
            ) {
                Text("Ajukan Penempatan", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
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

//@Preview
//@Composable
//fun PreviewDetailScreen() {
//    DetailScreen()
//}

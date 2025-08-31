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
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.arrow_forward
import simfan.composeapp.generated.resources.simfan_websuite

@Composable
fun DetailBPRScreen(
    onBackClick: () -> Unit = {},
    onAjukanClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
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
                text = "Detail BPR",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Isi
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            // Detail BPR
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(Modifier.padding(16.dp)) {
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
                            Text(
                                "BPR Sejahtera",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "DKI Jakarta",
                                fontSize = 11.sp,
                                color = Color(0xFF999999)
                            )
                        }
//                        Icon(
//                            painter = painterResource(Res.drawable.arrow_forward),
//                            contentDescription = null,
//                            tint = Color.Gray
//                        )
                    }
//                    Text("BPR Sejahtera", fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                    Text("DKI Jakarta", fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 12.dp))
                    DividerLine(Modifier.padding(vertical = 15.dp))
                    InfoRow("Kode OJK", "928376")
                    InfoRow("Kode Produk / UID", "BPR-250724–105678–0002")
                    InfoRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar")
                    InfoRow("Sisa Limit Deposito", "Rp1.500.000.000")
                    InfoRow("Total Transaksi BPR", "12 Transaksi")
                    DividerLine(Modifier.padding(vertical = 15.dp))

                    // Neraca Keuangan
                    Text(
                        "Neraca Keuangan (Periode 01 Juli 2025)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    InfoRow("Aset", "Rp750.000")
                    InfoRow("Kewajiban", "Rp250.000")
                    InfoRow("Ekuitas", "Rp500.000")
                    InfoRow("Loan to Deposito Ratio", "65%")
                }
            }
        }

        // Bottom Button
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
                Text("Ajukan Deposito", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


@Preview
@Composable
fun PreviewDetailBPRScreen() {
    DetailBPRScreen()
}

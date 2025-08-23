package org.kmp.simfan.screen.product.detail.subdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.simfan_websuite

@Composable
fun SubDetailScreen(
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6)) // bg_secondary
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
                text = "Detail Simfan Websuite",
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

        // 🔹 Konten Scrollable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // 🔹 Bagian Atas
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.simfan_websuite),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .size(38.dp)
                                .background(Color.LightGray, CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                        ) {
                            Text(
                                text = "Simfan Websuite",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Text(
                                text = "DKI Jakarta",
                                fontSize = 11.sp,
                                color = Color(0xFF999999)
                            )
                        }
                    }

                    DividerLine()

                    // 🔹 Bagian Tengah
                    Column(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    ) {
                        InfoRow("Kode OJK", "928376")
                        InfoRow("Kode Produk / UID", "BPR-250724–105678–0002")
                        InfoRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar")
                        InfoRow("Sisa Limit Deposito", "Rp1.500.000.000")
                        InfoRow("Total Transaksi BPR", "12 Transaksi")
                    }

                    DividerLine()

                    // 🔹 Bagian Bawah
                    Column(modifier = Modifier.padding(top = 12.dp)) {
                        InfoRow("Neraca Keuangan", "Periode 1 Juli 2025", boldLabel = true)
                        InfoRow("Aset", "Rp750.000")
                        InfoRow("Kewajiban", "Rp250.000")
                        InfoRow("Ekuitas", "Rp500.000")
                        InfoRow("Loan to Deposito Ratio", "65%")
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String, boldLabel: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (boldLabel) FontWeight.SemiBold else FontWeight.Normal,
            color = Color(0xFF22242F),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF22242F)
        )
    }
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE0E0E0))
    )
}

@Preview
@Composable
fun PreviewSubDetail() {
    SubDetailScreen()
}

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
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
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.simfan_websuite


@Composable
fun DetailBprScreen(
    title: String = "BPR Simfan",
    subtitle: String = "Bank Perkreditan Rakyat",
    navController: NavController,


) {
    Column {
        //app bar
        CustomAppBar(navController)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
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
                                Text(
                                    title,
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    subtitle,
                                    fontSize = 11.sp,
                                    color = Color(0xFF999999)
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE5E7EB))
                    }
                }

                // Section 2 - Informasi umum
                Column(
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    InfoRow("Kode OJK", "123456")
                    InfoRow("Kode Produk / UID", "BPR-250724–105678–0002")
                    InfoRow("Dijamin LPS", "Ya, Sampai dengan 2 Miliar")
                    InfoRow("Sisa Limit Deposito", "Rp 25.000.000")
                    InfoRow("Total Transaksi BPR", "150 Transaksi")
                }

                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE5E7EB))

                // Section 3 - Informasi lain
                Column(
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Neraca Keuangan",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Periode 01 Juli 2025",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                    InfoRow("Aset", "Rp750.000")
                    InfoRow("Kewajiban", "Rp250.000")
                    InfoRow("Ekuitas", "Rp500.000")
                    InfoRow("Loan to Deposito Ratio", "65%")

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
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}


package org.kmp.simfan.screen.product.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1

@Composable
fun FilterScreen(
    navController: NavController,
    currentRoute: Routes?,
    onClose: () -> Unit
) {
    var selectedTenor by remember { mutableStateOf("3 Bulan") }
    var selectedProduct by remember { mutableStateOf("E-Deposito") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("✕", fontSize = 20.sp, modifier = Modifier.clickable { onClose() })
            Text("Filter", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(20.dp)) // biar simetris
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Dana Deposito Awal
        Text("Dana Deposito Awal", fontWeight = FontWeight.Medium, fontSize = 14.sp)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Contoh: Rp1.000.000") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bunga Deposito Tahunan
        Text("Bunga Deposito Tahunan", fontWeight = FontWeight.Medium, fontSize = 14.sp)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Contoh: 6,5") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tenor
        Text("Tenor", fontWeight = FontWeight.Medium, fontSize = 14.sp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(Color(0xFFF8F8F8), RoundedCornerShape(12.dp))
        ) {
            listOf("1 Bulan", "3 Bulan", "6 Bulan", "12 Bulan").forEach { tenor ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedTenor = tenor }
                        .background(if (selectedTenor == tenor) Color(0xFFE6ECFF) else Color.Transparent)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tenor,
                        fontWeight = if (selectedTenor == tenor) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTenor == tenor) Color(0xFF2956EC) else Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (selectedTenor == tenor) {
                        Text("✓", color = Color(0xFF2956EC))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tipe Produk
        Text("Tipe Produk", fontWeight = FontWeight.Medium, fontSize = 14.sp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(Color(0xFFF8F8F8), RoundedCornerShape(12.dp))
        ) {
            listOf("Bilyet Fisik", "E-Deposito", "Bunga Bulanan", "Simfan").forEach { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedProduct = product }
                        .background(if (selectedProduct == product) Color(0xFFE6ECFF) else Color.Transparent)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product,
                        fontWeight = if (selectedProduct == product) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedProduct == product) Color(0xFF2956EC) else Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (selectedProduct == product) {
                        Text("✓", color = Color(0xFF2956EC))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { /* reset */ },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Atur Ulang", color = Color.Black)
            }

            Button(
                onClick = { /* apply filter */ },
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Button1)
            ) {
                Text("Terapkan Hasil", color = Color.White)
            }
        }
    }
}

//@Preview
//@Composable
//fun FilterScreenPreview() {
//    FilterScreen()
//}

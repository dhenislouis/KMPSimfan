package org.kmp.simfan.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.navigation.BottomBar
import org.kmp.simfan.extension.toRupiah
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

@Composable
fun HomeScreen(navController: NavController, currentRoute: Routes?) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { navController.navigate(it) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .verticalScroll(rememberScrollState())
        ) {
            AppBarSection()
            ProfileHeader()
            SaldoCard()
            SimulasiCard()
            ProdukRekomendasi()
            DepositoAktif()
        }
    }

}

@Composable
fun AppBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.simfan_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(78.dp)
                .height(29.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(Res.drawable.notification),
                contentDescription = "Notif",
                tint = Color(0xFF003FFC),
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0xFFEFF3FF), CircleShape)
                    .padding(6.dp)
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(Res.drawable.ic_headset),
                contentDescription = "Help",
                tint = Color(0xFF003FFC),
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0xFFEFF3FF), CircleShape)
                    .padding(6.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFFEEF2FF), Color(0xFFEFF6FF))
                )
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ayu_cantika),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Good Morning!", fontSize = 12.sp, color = Color.Gray)
            Text("Cahaya Ayu Cantika", fontSize = 14.sp, color = Color.Black)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("Location", fontSize = 10.sp, color = Color.Gray)
            Text("Jakarta", fontSize = 12.sp, color = Color.Black)
        }
    }
}

@Composable
fun SaldoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFB8A1BAE0),
                            Color(0xFF4A68FF),
                            Color(0xFF4A68FF)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Total Saldo", fontSize = 12.sp, color = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(Res.drawable.eye_on),
                        contentDescription = "eye",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text("Rp13.094.000", fontSize = 28.sp, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Tabungan", fontSize = 12.sp, color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = "go",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text("Rp13.094.000", fontSize = 15.sp, color = Color.White)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Deposito", fontSize = 12.sp, color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = "go",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text("Rp0", fontSize = 15.sp, color = Color.White)
                        Text("Bunga hingga 6.5%", fontSize = 11.sp, color = Color.White)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimulasiCard() {
    var jumlah by remember { mutableStateOf(1_509_000f) }
    var expandedDurasi by remember { mutableStateOf(false) }
    var selectedDurasi by remember { mutableStateOf("12 Bulan") }
    val durasiList = listOf("1 Bulan", "3 Bulan", "6 Bulan", "12 Bulan", "24 Bulan")

    var expandedBunga by remember { mutableStateOf(false) }
    var selectedBunga by remember { mutableStateOf("6.5% p.a") }
    val bungaList = listOf("5.0% p.a", "5.5% p.a", "6.0% p.a", "6.5% p.a", "7.0% p.a")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Simulasi Deposito", fontSize = 16.sp, color = Color(0xFF083FE5))
            Spacer(modifier = Modifier.height(12.dp))

            Text("Jumlah Deposito", fontSize = 13.sp, color = Color.Gray)
            Text("Rp${jumlah.toInt()}", fontSize = 18.sp, color = Color.Black)
            Slider(
                value = jumlah,
                onValueChange = { jumlah = it },
                valueRange = 1_000_000f..100_000_000f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = expandedDurasi, onExpandedChange = { expandedDurasi = it }) {
                TextField(
                    value = selectedDurasi,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Durasi") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDurasi) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expandedDurasi, onDismissRequest = { expandedDurasi = false }) {
                    durasiList.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedDurasi = it
                                expandedDurasi = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            ExposedDropdownMenuBox(expanded = expandedBunga, onExpandedChange = { expandedBunga = it }) {
                TextField(
                    value = selectedBunga,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Suku Bunga") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedBunga) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expandedBunga, onDismissRequest = { expandedBunga = false }) {
                    bungaList.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedBunga = it
                                expandedBunga = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Hitung logika */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003FFC)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Hitung", color = Color.White)
            }
        }
    }
}


@Composable
fun ProdukRekomendasi() {
    Column(Modifier.padding(16.dp)) {
        Text("Produk Deposito Rekomendasi", fontSize = 16.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .width(165.dp)
                    .padding(end = 12.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.simfan_websuite),
                        contentDescription = "Produk",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(133.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Text("Simfan WebSuite", fontSize = 13.sp, color = Color.Black)
                    Text("120 orang", fontSize = 12.sp, color = Color(0xFF003FFC))
                    Text("6.5% p.a", fontSize = 13.sp, color = Color(0xFFF78208))
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun DepositoAktif() {
    Column(Modifier.padding(16.dp)) {
        Text("Deposito Aktif", fontSize = 16.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .width(165.dp)
                    .padding(end = 12.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.simfan_websuite),
                        contentDescription = "Deposito",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(133.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Text("Simfan WebSuite", fontSize = 13.sp, color = Color.Black)
                    Text("Tenor 3 bulan", fontSize = 11.sp, color = Color.Red)
                    Text("Rp100.000.000", fontSize = 13.sp, color = Color(0xFF003FFC))
                    Text("6.5% p.a", fontSize = 13.sp, color = Color(0xFFF78208))
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

//@Preview()
//@Composable
//fun PreviewHome() {
//    HomeScreen(na)
//}

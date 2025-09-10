package org.kmp.simfan.screen.product.productdeposito.productdeposito

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
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjukanPenempatanProdukDepositoScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onAjukanClick: () -> Unit = {},
    onTandaTangan: () -> Unit = {}
) {
    var checked by remember { mutableStateOf(false) }
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            // Bottom Action Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol utama "Ajukan"
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
            // ---------- KARTU DETAIL DEPOSITO ----------
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
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

            // ---------- INFO TAMBAHAN (contoh ARO, rekening, dll) ----------
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

            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Perpanjangan Deposito (ARO)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF22242F),
                        modifier = Modifier.padding(16.dp, 12.dp, 16.dp, 8.dp)
                    )

                    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5FF))
                    ) {
                        Column() {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "ARO (Automatic Roll Over)",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF22242F)
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
                                "ARO (Automatic Roll Over) atau Perpanjangan Deposito Otomatis adalah fitur otomatis untuk memperpanjang deposito dengan nominal dan tenor yang sama, tanpa perlu pengajuan ulang. Bunga akan dicairkan setelah jatuh tempo.",
                                fontSize = 12.sp,
                                color = Color(0xFF666666),
                                lineHeight = 16.sp
                            )

                            Spacer(Modifier.height(12.dp))

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFDEE9FF))
                            ) {
                                Row(
                                    Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_info),
                                        contentDescription = null,
                                        tint = Color(0xFFF89227),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "Perubahan ARO hanya dapat dilakukan ketika status deposito Kamu telah aktif.",
                                        fontSize = 12.sp,
                                        color = Color(0xFF22242F),
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
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

                    // Pilih Akun Bank (Row seperti contoh kamu)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true } // buka dropdown saat ditekan
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

//@Preview
//@Composable
//fun PreviewDetailDeposito() {
//    AjukanPenempatanProdukDepositoScreen()
//}

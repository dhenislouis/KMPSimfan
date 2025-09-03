package org.kmp.simfan.screen.simpananku.depositosimpananku

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.label_deposito
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.aro
import simfan.composeapp.generated.resources.bca_logo
import simfan.composeapp.generated.resources.ic_info

@Composable
fun AjukanpenempatanSimfankuScreen(
    onBackClick: () -> Unit = {},
    onAjukanClick: () -> Unit = {},
    onTandaTangan: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                // Tombol back
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

                // Judul
                Text(
                    text = "Ajukan Penempatan",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
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

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.bca_logo), // ganti sesuai drawable Anda
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
    }
}

@Preview
@Composable
fun PreviewDetailDeposito() {
    AjukanpenempatanSimfankuScreen()
}

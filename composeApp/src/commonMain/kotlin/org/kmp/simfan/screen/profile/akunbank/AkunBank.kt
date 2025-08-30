package org.kmp.simfan.screen.profile.akunbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.*

/**
 * 🚀 Voyager Screen AkunBank
 */
object AkunBankScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        AkunBankUI(
            onBackClick = { navigator.pop() },
            onTambahAkunClick = {
                // contoh navigasi ke screen tambah akun
                navigator.push(AkunBankScreen)
            },
            onSimpanClick = {
                // TODO: aksi simpan data akun
                navigator.pop()
            }
        )
    }
}

@Composable
fun AkunBankUI(
    onBackClick: () -> Unit = {},
    onTambahAkunClick: () -> Unit = {},
    onSimpanClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA)) // bg_secondary
    ) {
        // 🔹 TopBar manual
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
                text = "Akun bank",
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

        // 🔹 Konten scrollable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Bagian judul & deskripsi
            Text(
                text = "Akun Bank",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rekening utama yang digunakan untuk pencairan dana hasil deposito dan cashback. Pastikan data sesuai untuk proses transfer otomatis.",
                fontSize = 12.sp,
                color = Color(0xFF999999),
                lineHeight = 16.sp
            )

            // 🔹 Tambah akun bank card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp)
                    .clickable { onTambahAkunClick() },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.tambahakun),
                        contentDescription = "Tambah Akun Bank",
                        tint = Color(0xFF023FFC),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Tambah Akun Bank",
                        fontSize = 13.sp,
                        color = Color(0xFF181D27),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(Res.drawable.tambah),
                        contentDescription = "Tambah",
                        tint = Color(0xFFABABAB),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            )

            // 🔹 Akun Bank Utama
            Text("Akun Bank Utama", fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Rekening utama yang digunakan untuk pencairan dana hasil deposito dan cashback.",
                fontSize = 12.sp,
                color = Color(0xFF999999),
                lineHeight = 16.sp
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.logo_bca),
                        contentDescription = "Logo Bank",
                        modifier = Modifier
                            .width(59.dp)
                            .height(33.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Bank BCA", fontSize = 13.sp, color = Color.Black)
                        Text(
                            "726347818910",
                            fontSize = 12.sp,
                            color = Color(0xFF999999),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }

            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            )

            // 🔹 Akun Bank Lainnya
            Text("Akun Bank Lainnya", fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Rekening tambahan yang dapat digunakan untuk pencairan alternatif atau kebutuhan transaksi lainnya.",
                fontSize = 12.sp,
                color = Color(0xFF999999),
                lineHeight = 16.sp
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 14.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.logo_bca),
                        contentDescription = "Logo Bank",
                        modifier = Modifier
                            .width(59.dp)
                            .height(33.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Bank BCA", fontSize = 13.sp, color = Color.Black)
                        Text(
                            "726347818910",
                            fontSize = 12.sp,
                            color = Color(0xFF999999),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }

        // 🔹 Tombol simpan di bawah
        Surface(
            shadowElevation = 8.dp,
            color = Color.White
        ) {
            Button(
                onClick = onSimpanClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Button1)
            ) {
                Text("Simpan", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun PreviewAkunBankUI() {
    AkunBankUI()
}

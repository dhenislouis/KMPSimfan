package org.kmp.simfan.screen.profile.syaratketentuan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyaratKetentuanScreen(
    onBack: () -> Unit = {}
) {
    val syaratList = listOf(
        "1. Definisi" to "Deposito adalah produk simpanan berjangka dengan suku bunga tetap, di mana dana nasabah akan disimpan dalam jangka waktu tertentu dan tidak dapat ditarik sebelum jatuh tempo, kecuali atas persetujuan khusus.",
        "2. Penempatan Dana" to "Minimum penempatan dana untuk membuka deposito adalah sebesar Rp1.000.000,- atau sesuai ketentuan yang berlaku. Penempatan dana dilakukan melalui metode pembayaran yang tersedia, seperti transfer bank, virtual account, atau metode lain yang disediakan oleh platform.",
        "3. Jangka Waktu" to "Pilihan tenor deposito tersedia mulai dari 1 bulan, 3 bulan, 6 bulan, hingga 12 bulan, tergantung produk dan mitra BPR yang dipilih oleh pengguna. Dana tidak dapat dicairkan sebelum jatuh tempo (breakable), kecuali pada produk tertentu yang menyatakan sebaliknya.",
        "4. Suku Bunga dan Perhitungan" to "Suku bunga bersifat tetap selama jangka waktu yang telah disepakati dan ditentukan pada saat penempatan deposito.\n\nPerhitungan bunga menggunakan metode gross (belum dipotong pajak), dan akan dikenakan pajak sesuai ketentuan perpajakan yang berlaku di Indonesia.",
        "5. Pencairan Dana" to "Setelah jatuh tempo, dana pokok dan bunga bersih (setelah dipotong pajak) akan ditransfer secara otomatis ke rekening bank yang telah didaftarkan oleh nasabah pada saat registrasi.\nUntuk pencairan sebelum jatuh tempo, bunga tidak akan diberikan, dan nasabah harus mengajukan permintaan resmi ke pihak penyedia layanan.",
        "6. Risiko dan Jaminan" to "yang bekerja sama dijamin oleh Lembaga Penjamin Simpanan (LPS) hingga batas maksimal sesuai regulasi, yakni Rp2 miliar per nasabah per bank. Pengguna disarankan membaca detail produk masing-masing sebelum melakukan penempatan.",
        "7. Perubahan Ketentuan" to "Pihak penyedia layanan berhak untuk mengubah syarat dan ketentuan ini sewaktu-waktu, dengan pemberitahuan sebelumnya kepada pengguna melalui media resmi seperti email atau aplikasi.",
        "8. Lain-lain" to "Dengan menyetujui syarat dan ketentuan ini, pengguna menyatakan bahwa seluruh informasi yang diberikan adalah benar dan dapat dipertanggungjawabkan. Pengguna juga menyatakan setuju untuk tunduk pada hukum dan peraturan yang berlaku di wilayah Republik Indonesia."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        // 🔹 AppBar
        TopAppBar(
            title = { Text("Syarat & Ketentuan", fontWeight = FontWeight.SemiBold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        // 🔹 List isi
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(syaratList) { (judul, isi) ->
                Column {
                    Text(
                        text = judul,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF252C32),
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                    )
                    Text(
                        text = isi,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 18.sp,
                        color = Color(0xFF22242F)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSyaratKetentuan() {
    SyaratKetentuanScreen()
}

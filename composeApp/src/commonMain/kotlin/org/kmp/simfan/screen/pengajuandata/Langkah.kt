package org.kmp.simfan.screen.pengajuandata

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
 * 🚀 Voyager Screen Langkah
 */
object LangkahScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        LangkahUI(
            onBack = { navigator.pop() },
            onLanjut = {
                navigator.push(Langkah1Screen)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LangkahUI(
    onBack: () -> Unit = {},
    onLanjut: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Langkah Pengajuan Data",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                actions = {
                    Spacer(Modifier.size(48.dp)) // balance kanan
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Column(Modifier.padding(bottom = 18.dp)) {
                    Row {
                        Text(
                            "Saya telah membaca dan menyetujui",
                            fontSize = 13.5.sp,
                            color = Color(0xFF252C32)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            "Syarat &",
                            fontSize = 13.5.sp,
                            color = Color(0xFF003FFC)
                        )
                    }
                    Row {
                        Text(
                            "Ketentuan yang berlaku",
                            fontSize = 13.5.sp,
                            color = Color(0xFF003FFC)
                        )
                        Text(
                            ".",
                            fontSize = 13.5.sp,
                            color = Color(0xFF252C32)
                        )
                    }
                }

                Button(
                    onClick = onLanjut,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Lanjut",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // bg_secondary
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                "Mulai Rekeningmu di Sini!",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )

            Text(
                "Ikuti langkah mudah untuk membuka rekening digital dalam hitungan menit - mulai dari verifikasi hingga aktivasi.",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            val langkahList = listOf(
                "Unggah foto E-KTP dengan jelas",
                "Ambil selfie untuk proses verifikasi wajah",
                "NPWP (Opsional)",
                "Isi lengkap data diri sesuai identitas",
                "Buat PIN keamanan untuk akun anda"
            )

            langkahList.forEachIndexed { index, text ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF78208)), // orange
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${index + 1}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLangkahUI() {
    LangkahUI()
}
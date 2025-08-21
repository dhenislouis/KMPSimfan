package org.kmp.simfan.screen.bukarekening

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Langkah1Screen(
    onBack: () -> Unit = {},
    onAmbilFoto: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Buka Rekening",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onAmbilFoto,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003FFC))
                ) {
                    Text(
                        "Ambil Foto e-KTP",
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
                .background(Color(0xFFF4F4F4))
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // === Header Langkah ===
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Langkah 1 dari 4",
                    fontSize = 11.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Progress bar (stepper)
                LinearProgressIndicator(
                    progress = 0.25f, // 1 dari 4 langkah
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(MaterialTheme.shapes.small),
                    color = Color(0xFF003FFC),
                    trackColor = Color(0xFFE0E0E0)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Unggah foto e-KTP dengan jelas",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Ikuti langkah mudah untuk membuka rekening digital dalam hitungan menit — mulai dari verifikasi hingga aktivasi.",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            // === Kartu Keamanan ===
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 35.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 25.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.lock),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            "Kami menjamin semua data Anda terlindungi dengan",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                        Text(
                            "teknologi enkripsi.",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }

                // Gambar KTP
                Box(
                    modifier = Modifier
                        .width(238.dp)
                        .height(206.dp)
                        .padding(bottom = 80.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ktp_pengajuan_deposito),
                        contentDescription = "Ilustrasi KTP",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Judul "Siapkan e-KTP anda"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Siapkan",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "e-KTP",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF78208)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "anda",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLangkah1Screen() {
    Langkah1Screen()
}

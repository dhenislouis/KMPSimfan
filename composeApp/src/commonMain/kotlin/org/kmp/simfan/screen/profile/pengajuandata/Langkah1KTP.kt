package org.kmp.simfan.screen.profile.pengajuandata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kmp.simfan.components.camera.CameraView
import org.kmp.simfan.core.Button1
import kotlin.experimental.and

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Langkah1KTPScreen(
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengajuan Data", fontSize = 20.sp, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
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
                if (photoBytes != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { photoBytes = null },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                        ) {
                            Text("Ulangi", color = Color.White)
                        }

                        Spacer(Modifier.width(16.dp))

                        Button(
                            onClick = onNext,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Button1)
                        ) {
                            Text("Lanjut", color = Color.White)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {



        }
    }
}

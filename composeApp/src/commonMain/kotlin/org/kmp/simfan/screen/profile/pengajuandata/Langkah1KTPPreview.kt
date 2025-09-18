package org.kmp.simfan.screen.profile.pengajuandata

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.kmp.simfan.Routes
import org.kmp.simfan.utils.toImageBitmap

@Composable
fun KtpPreviewScreen(
    navController: NavController,
    imageData: ByteArray
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val imageBitmap = remember(imageData) { imageData.toImageBitmap() }

            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Preview Foto KTP",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                        .padding(16.dp)
                )
            } else {
                Text("Gagal memuat foto KTP", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Ulangi")
                }
                Button(onClick = {
                    navController.navigate(Routes.Langkah2Panduan) {
                        popUpTo(Routes.Langkah1KTP) { inclusive = true }
                        launchSingleTop = true
                    }
                }) {
                    Text("Gunakan Foto")
                }
            }
        }
    }
}
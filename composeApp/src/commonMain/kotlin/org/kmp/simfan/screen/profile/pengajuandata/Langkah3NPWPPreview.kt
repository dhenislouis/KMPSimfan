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
import org.kmp.simfan.screen.profile.pengajuandata.npwp.NpwpViewModel
import org.kmp.simfan.utils.toImageBitmap
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NpwpPreviewScreen(
    navController: NavController,
    imageData: ByteArray
) {
    val viewModel: NpwpViewModel = koinViewModel()

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
                    contentDescription = "Preview Foto NPWP",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                        .padding(16.dp)
                )
            } else {
                Text("Gagal memuat foto NPWP", color = Color.Red)
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
                    viewModel.getOcr(imageData)

                    navController.navigate(Routes.Langkah3) {
                        popUpTo(Routes.Langkah3NPWP) { inclusive = true }
                        launchSingleTop = true
                    }
                }) {
                    Text("Gunakan Foto")
                }
            }
        }
    }
}

//@Composable
//fun NpwpPreviewScreen(
//    navController: NavController,
//    imageData: ByteArray,
//    state: NpwpState,
//    onGunakanFoto: (ByteArray) -> Unit,
//    onClear: () -> Unit,
//    onSuccess: () -> Unit
//) {
//    Scaffold { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            val imageBitmap = remember(imageData) { imageData.toImageBitmap() }
//
//            Text("Preview Foto NPWP", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            Spacer(Modifier.height(16.dp))
//
//            if (imageBitmap != null) {
//                Image(
//                    bitmap = imageBitmap,
//                    contentDescription = "Preview NPWP",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .aspectRatio(3f / 2f)
//                        .padding(16.dp)
//                )
//            } else {
//                Text("Gagal memuat foto", color = Color.Red)
//            }
//
//            Spacer(Modifier.height(32.dp))
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 32.dp)
//            ) {
//                Button(onClick = { navController.popBackStack() }) {
//                    Text("Ulangi")
//                }
//                Button(onClick = { onGunakanFoto(imageData) }) {
//                    Text("Gunakan Foto")
//                }
//            }
//
//            Spacer(Modifier.height(16.dp))
//
//            when (state) {
//                is NpwpState.Loading -> {
//                    CircularProgressIndicator()
//                }
//                is NpwpState.Success -> {
//                    Text(
//                        "OCR Result: ${state.data.npwpNumber} " +
//                                "(confidence: ${state.data.confidence ?: "-"} )",
//                        color = Color.Green
//                    )
//                    LaunchedEffect(state) { onSuccess() }
//                }
//                is NpwpState.Error -> {
//                    AlertDialog(
//                        onDismissRequest = onClear,
//                        title = { Text("Error") },
//                        text = { Text(state.message) },
//                        confirmButton = {
//                            TextButton(onClick = onClear) { Text("OK") }
//                        }
//                    )
//                }
//                else -> Unit
//            }
//
//        }
//    }
//}

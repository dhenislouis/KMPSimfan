package org.kmp.simfan.screen.profile.pengajuandata

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kashif.cameraK.controller.CameraController
import com.kashif.cameraK.enums.CameraLens
import com.kashif.cameraK.enums.Directory
import com.kashif.cameraK.enums.FlashMode
import com.kashif.cameraK.enums.ImageFormat
import com.kashif.cameraK.permissions.Permissions
import com.kashif.cameraK.permissions.providePermissions
import com.kashif.cameraK.result.ImageCaptureResult
import com.kashif.cameraK.ui.CameraPreview
import kotlinx.coroutines.launch
import org.kmp.simfan.Routes
import org.kmp.simfan.components.button.ButtonCamera
import org.kmp.simfan.components.button.ButtonFlash
import org.kmp.simfan.components.dialog.ErrorDialog
import org.kmp.simfan.components.dialog.InfoDialog
import org.kmp.simfan.components.dialog.ProgressDialog
import org.kmp.simfan.components.icon.FrameKtp
import org.kmp.simfan.screen.profile.pengajuandata.ktp.KtpState

@Composable
fun Langkah1KTPScreen(
    navController: NavController,
    currentRoute: Routes?,
    state: KtpState,
    onBackPressed: () -> Unit = {},
    onCapture: (ByteArray) -> Unit = {},
    onClear: () -> Unit = {},
) {
    val permissions: Permissions = providePermissions()
    val hasCameraPermission = remember { mutableStateOf(permissions.hasCameraPermission()) }

    if (!hasCameraPermission.value) {
        permissions.RequestCameraPermission(
            onGranted = { hasCameraPermission.value = true },
            onDenied = onBackPressed
        )
    } else {
        Scaffold { _ ->
            CameraScreen(
                onCapture = { byteArray ->
                    onCapture(byteArray)

                    navController.navigate(Routes.Langkah2Panduan) {
                        popUpTo(Routes.Langkah1KTP) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )

            when (state) {
                is KtpState.Idle -> Unit
                is KtpState.Loading -> ProgressDialog(isShowing = true)
                is KtpState.Success -> InfoDialog(
                    isShowing = true,
                    data = state.data,
                    onDismissRequest = onClear,
                )
                is KtpState.Error -> ErrorDialog(
                    isShowing = true,
                    message = state.message,
                    onDismissRequest = onClear,
                )
            }
        }
    }
}

@Composable
fun CameraScreen(
    onCapture: (ByteArray) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val cameraController = remember { mutableStateOf<CameraController?>(null) }
    val flashCameraState = remember { mutableStateOf(FlashMode.OFF) }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            cameraConfiguration = {
                setCameraLens(CameraLens.BACK)
                setFlashMode(flashCameraState.value)
                setImageFormat(ImageFormat.JPEG)
                setDirectory(Directory.PICTURES)
            },
            onCameraControllerReady = { camController ->
                cameraController.value = camController
            }
        )

        // Overlay Frame KTP
        Image(
            modifier = Modifier.fillMaxSize(),
            imageVector = FrameKtp,
            contentDescription = "Frame KTP",
        )

        // Instruksi
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            text = "Posisikan kartu KTP di dalam bingkai",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
            ),
        )

        // Tombol bawah
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .statusBarsPadding()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 40.dp),
                text = "Pastikan KTP terlihat jelas dan semua teks terbaca",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                // Tombol capture
                ButtonCamera(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        scope.launch {
                            when (val result = cameraController.value?.takePicture()) {
                                is ImageCaptureResult.Success -> {
                                    val byteArray = result.byteArray
                                    println("📸 Foto KTP Success: size=${byteArray.size} bytes")
                                    onCapture(byteArray)
                                }

                                is ImageCaptureResult.Error -> {
                                    println("❌ Foto KTP Error: ${result.exception.message}")
                                }

                                null -> {
                                    println("⚠️ CameraController is null")
                                }
                            }
                        }
                    },
                )

                // Tombol flash
                ButtonFlash(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 28.dp),
                    flashMode = flashCameraState.value,
                ) {
                    if (flashCameraState.value == FlashMode.OFF) {
                        flashCameraState.value = FlashMode.ON
                        cameraController.value?.setFlashMode(FlashMode.ON)
                    } else {
                        flashCameraState.value = FlashMode.OFF
                        cameraController.value?.setFlashMode(FlashMode.OFF)
                    }
                }
            }
        }
    }
}

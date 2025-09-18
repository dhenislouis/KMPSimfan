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
import org.kmp.simfan.components.dialog.InfoDialogNpwp
import org.kmp.simfan.components.dialog.ProgressDialog
import org.kmp.simfan.components.icon.FrameKtp
import org.kmp.simfan.components.icon.FrameNpwp
import org.kmp.simfan.screen.profile.pengajuandata.npwp.NpwpState
import org.kmp.simfan.screen.profile.pengajuandata.npwp.NpwpViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Langkah3NPWPScreen(
    navController: NavController,
    currentRoute: Routes?,
    state: NpwpState,
    onBackPressed: () -> Unit = {},
    onCapture: (ByteArray) -> Unit = {},
    onClear: () -> Unit = {},
) {
    val permissions: Permissions = providePermissions()
    val hasCameraPermission = remember { mutableStateOf(permissions.hasCameraPermission()) }
    val viewModel: NpwpViewModel = koinViewModel()

    if (!hasCameraPermission.value) {
        permissions.RequestCameraPermission(
            onGranted = { hasCameraPermission.value = true },
            onDenied = onBackPressed
        )
    } else {
        Scaffold { _ ->
            CameraScreenNPWP(
                onCapture = { byteArray ->
                    viewModel.getOcr(byteArray)

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("npwp_image", byteArray)

                    navController.navigate(Routes.Langkah3NPWPPreview)
                }
            )

            when (state) {
                is NpwpState.Idle -> Unit
                is NpwpState.Loading -> ProgressDialog(isShowing = true)
                is NpwpState.Success -> InfoDialogNpwp(
                    isShowing = true,
                    data = state.data,
                    onDismissRequest = onClear,
                )
                is NpwpState.Error -> ErrorDialog(
                    isShowing = true,
                    message = state.message,
                    onDismissRequest = onClear,
                )
            }
        }
    }
}

@Composable
fun CameraScreenNPWP(
    onCapture: (ByteArray) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val cameraController = remember { mutableStateOf<CameraController?>(null) }
    val flashCameraState = remember { mutableStateOf(FlashMode.OFF) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera Preview
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

        // Overlay Frame NPWP
        Image(
            modifier = Modifier.fillMaxSize(),
            imageVector = FrameNpwp,
            contentDescription = "Frame NPWP",
        )

        // Instruksi
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            text = "Posisikan kartu NPWP di dalam bingkai",
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
                text = "Pastikan NPWP terlihat jelas dan semua teks terbaca",
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
                                    println("📸 Foto NPWP Success: size=${byteArray.size} bytes")
                                    onCapture(byteArray)
                                }

                                is ImageCaptureResult.Error -> {
                                    println("❌ Foto NPWP Error: ${result.exception.message}")
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

//@Composable
//fun Langkah3NpwpScreen(
//    navController: NavController,
//    currentRoute: Routes?,
//    state: NpwpState,
//    onBackPressed: () -> Unit = {},
//    onCapture: (ByteArray) -> Unit = {},
//    onClear: () -> Unit = {},
//) {
//    val permissions: Permissions = providePermissions()
//    val hasCameraPermission = remember { mutableStateOf(permissions.hasCameraPermission()) }
//
//    if (!hasCameraPermission.value) {
//        permissions.RequestCameraPermission(
//            onGranted = { hasCameraPermission.value = true },
//            onDenied = onBackPressed
//        )
//    } else {
//        Scaffold { _ ->
//            NpwpCameraScreen(
//                onCapture = { byteArray ->
//                    // simpan foto ke savedStateHandle
//                    navController.currentBackStackEntry
//                        ?.savedStateHandle
//                        ?.set("npwp_image", byteArray)
//
//                    // pindah ke preview screen
//                    navController.navigate(Routes.Langkah3NPWPPreview)
//                }
//            )
//
//            // dialog hanya muncul kalau di preview, bukan di kamera
//            when (state) {
//                is NpwpState.Idle -> Unit
//                is NpwpState.Loading -> ProgressDialog(isShowing = true)
//                is NpwpState.Success -> InfoDialogNpwp(
//                    isShowing = true,
//                    data = state.data,
//                    onDismissRequest = onClear,
//                )
//                is NpwpState.Error -> ErrorDialog(
//                    isShowing = true,
//                    message = state.message,
//                    onDismissRequest = onClear,
//                )
//            }
//        }
//    }
//}
//
//
//@Composable
//fun NpwpCameraScreen(
//    onCapture: (ByteArray) -> Unit = {},
//) {
//    val scope = rememberCoroutineScope()
//    val cameraController = remember { mutableStateOf<CameraController?>(null) }
//    val flashCameraState = remember { mutableStateOf(FlashMode.OFF) }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        // Camera Preview
//        CameraPreview(
//            modifier = Modifier.fillMaxSize(),
//            cameraConfiguration = {
//                setCameraLens(CameraLens.BACK)
//                setFlashMode(flashCameraState.value)
//                setImageFormat(ImageFormat.JPEG)
//                setDirectory(Directory.PICTURES)
//            },
//            onCameraControllerReady = { camController ->
//                cameraController.value = camController
//            }
//        )
//
//        // Overlay Frame KTP
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            imageVector = FrameKtp,
//            contentDescription = "Frame NPWP",
//        )
//
//        // Instruksi
//        Text(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .padding(top = 100.dp),
//            text = "Posisikan kartu NPWP di dalam bingkai",
//            style = MaterialTheme.typography.titleLarge.copy(
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                textAlign = TextAlign.Center,
//                color = Color.White,
//            ),
//        )
//
//        // Tombol bawah
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .navigationBarsPadding()
//                .statusBarsPadding()
//                .padding(bottom = 20.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                modifier = Modifier.padding(bottom = 40.dp),
//                text = "Pastikan NPWP terlihat jelas dan semua teks terbaca",
//                style = MaterialTheme.typography.titleMedium.copy(
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                )
//            )
//
//            Box(modifier = Modifier.fillMaxWidth()) {
//                // Tombol capture
//                ButtonCamera(
//                    modifier = Modifier.align(Alignment.Center),
//                    onClick = {
//                        scope.launch {
//                            when (val result = cameraController.value?.takePicture()) {
//                                is ImageCaptureResult.Success -> {
//                                    val byteArray = result.byteArray
//                                    println("📸 Foto NPWP Success: size=${byteArray.size} bytes")
//                                    onCapture(byteArray)
//                                }
//
//                                is ImageCaptureResult.Error -> {
//                                    println("❌ Foto NPWP Error: ${result.exception.message}")
//                                }
//
//                                null -> {
//                                    println("⚠️ CameraController is null")
//                                }
//                            }
//                        }
//                    },
//                )
//
//                // Tombol flash
//                ButtonFlash(
//                    modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .padding(end = 28.dp),
//                    flashMode = flashCameraState.value,
//                ) {
//                    if (flashCameraState.value == FlashMode.OFF) {
//                        flashCameraState.value = FlashMode.ON
//                        cameraController.value?.setFlashMode(FlashMode.ON)
//                    } else {
//                        flashCameraState.value = FlashMode.OFF
//                        cameraController.value?.setFlashMode(FlashMode.OFF)
//                    }
//                }
//            }
//        }
//    }
//}

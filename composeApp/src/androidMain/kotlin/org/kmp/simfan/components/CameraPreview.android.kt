//package org.kmp.simfan.components
//
//import android.Manifest
//import android.content.Context
//import android.util.Log
//import android.view.TextureView
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import kotlinx.coroutines.suspendCancellableCoroutine
//import java.io.ByteArrayOutputStream
//import java.nio.ByteBuffer
//import kotlin.coroutines.resume
//
//private var imageCapture: ImageCapture? = null
//
//@Composable
//actual fun CameraPreview(
//    modifier: Modifier,
//    onPhotoCaptured: (ByteArray) -> Unit
//) {
//    val context = LocalContext.current
//    AndroidView(
//        factory = { ctx ->
//            TextureView(ctx).also { view ->
//                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
//                cameraProviderFuture.addListener({
//                    val cameraProvider = cameraProviderFuture.get()
//
//                    val preview = Preview.Builder().build().apply {
//                        setSurfaceProvider(view.surfaceTextureListener)
//                    }
//
//                    imageCapture = ImageCapture.Builder().build()
//
//                    val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
//
//                    try {
//                        cameraProvider.unbindAll()
//                        cameraProvider.bindToLifecycle(
//                            ctx as androidx.lifecycle.LifecycleOwner,
//                            cameraSelector,
//                            preview,
//                            imageCapture
//                        )
//                    } catch (exc: Exception) {
//                        Log.e("CameraPreview", "Use case binding failed", exc)
//                    }
//                }, ContextCompat.getMainExecutor(ctx))
//            }
//        },
//        modifier = modifier
//    )
//}
//
//actual suspend fun takePicture(): ByteArray? = suspendCancellableCoroutine { cont ->
//    val capture = imageCapture ?: run {
//        cont.resume(null)
//        return@suspendCancellableCoroutine
//    }
//    val outputOptions = ImageCapture.OutputFileOptions.Builder(
//        java.io.File.createTempFile("photo", ".jpg")
//    ).build()
//
//    capture.takePicture(ContextCompat.getMainExecutor(null), object : ImageCapture.OnImageCapturedCallback() {
//        override fun onCaptureSuccess(imageProxy: androidx.camera.core.ImageProxy) {
//            val buffer: ByteBuffer = imageProxy.planes[0].buffer
//            val bytes = ByteArray(buffer.remaining())
//            buffer.get(bytes)
//            cont.resume(bytes)
//            imageProxy.close()
//        }
//
//        override fun onError(exception: ImageCaptureException) {
//            cont.resume(null)
//        }
//    })
//}

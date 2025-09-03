package org.kmp.simfan.components.camera

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import java.io.ByteArrayOutputStream

@Composable
actual fun CameraView(
    onPhotoTaken: (ByteArray) -> Unit,
    modifier: Modifier
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            try {
                val stream = ByteArrayOutputStream()
                it.compress(CompressFormat.JPEG, 100, stream)
                onPhotoTaken(stream.toByteArray())
            } catch (e: Exception) {
                Log.e("CameraView", "Gagal ambil foto", e)
            }
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(null)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text("📷 Membuka Kamera...", color = androidx.compose.ui.graphics.Color.Black)
    }
}

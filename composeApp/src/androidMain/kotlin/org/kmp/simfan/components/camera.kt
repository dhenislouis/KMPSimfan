package org.kmp.simfan.components

import android.view.TextureView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun CameraPreview(modifier: Modifier) {
    AndroidView(
        factory = { context ->
            TextureView(context).apply {
                clipToOutline = true
                // TODO: Tambahkan CameraX preview session di sini
            }
        },
        modifier = modifier
    )
}

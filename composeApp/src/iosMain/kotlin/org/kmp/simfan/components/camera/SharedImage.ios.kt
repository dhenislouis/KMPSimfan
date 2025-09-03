package org.kmp.simfan.components.camera

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

@Composable
actual fun SharedImage(
    photoBytes: ByteArray,
    modifier: Modifier
) {
    val skiaImage = Image.makeFromEncoded(photoBytes)

    // Workaround kalau asImageBitmap() tidak resolve di IDE
    val imageBitmap: ImageBitmap = org.jetbrains.skia.Image.makeFromEncoded(photoBytes).toComposeImageBitmap()

    Image(
        bitmap = imageBitmap,
        contentDescription = "Preview",
        modifier = modifier
    )
}

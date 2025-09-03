package org.kmp.simfan.components.camera

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap

@Composable
actual fun SharedImage(
    photoBytes: ByteArray,
    modifier: Modifier
) {
    val bitmap = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.size)
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "Preview",
        modifier = modifier
    )
}

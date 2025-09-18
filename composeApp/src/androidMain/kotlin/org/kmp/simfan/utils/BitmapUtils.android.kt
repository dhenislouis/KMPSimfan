package org.kmp.simfan.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import java.io.FileOutputStream

// Gunakan Android Bitmap sebagai PlatformBitmap
actual typealias PlatformBitmap = Bitmap

actual fun ImageBitmap.toPlatformBitmap(): Bitmap {
    return this.asAndroidBitmap()
}

actual fun savePlatformBitmapToFile(
    bitmap: Bitmap,
    fileName: String,
    context: Any?
): String? {
    val androidContext = context as? Context
        ?: throw IllegalArgumentException("Context wajib dikirim di Android")

    val file = File(androidContext.cacheDir, fileName)
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file.absolutePath
}

actual fun ByteArray.toImageBitmap(): ImageBitmap? {
    val bitmap = BitmapFactory.decodeByteArray(this, 0, this.size)
    return bitmap?.asImageBitmap()
}

package org.kmp.simfan.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String = "signature.png"): String {
    // Lokasi: cacheDir (bisa diganti ke filesDir atau externalFilesDir kalau perlu)
    val file = File(context.cacheDir, fileName)

    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }

    return file.absolutePath
}
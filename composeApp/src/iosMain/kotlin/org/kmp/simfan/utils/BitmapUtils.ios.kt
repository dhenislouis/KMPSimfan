package org.kmp.simfan.utils

import androidx.compose.ui.graphics.ImageBitmap
import platform.UIKit.UIImage
import platform.Foundation.*
import platform.UIKit.UIImagePNGRepresentation

// Gunakan UIImage sebagai PlatformBitmap
actual typealias PlatformBitmap = UIImage

// ⚠️ Perlu implementasi konversi valid dari ImageBitmap → UIImage
// Untuk sementara bisa error agar mudah debug
actual fun ImageBitmap.toPlatformBitmap(): UIImage {
    error("Konversi ImageBitmap → UIImage perlu diimplementasikan")
}

actual fun savePlatformBitmapToFile(
    bitmap: UIImage,
    fileName: String,
    context: Any?
): String? {
    val docsDir = NSSearchPathForDirectoriesInDomains(
        NSDocumentDirectory,
        NSUserDomainMask,
        true
    ).firstOrNull() as? String ?: return null

    val filePath = "$docsDir/$fileName"
    val fileUrl = NSURL.fileURLWithPath(filePath)

    val imageData = UIImagePNGRepresentation(bitmap) ?: return null
    imageData.writeToURL(fileUrl, true)

    return filePath
}

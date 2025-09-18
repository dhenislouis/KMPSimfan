// BitmapUtils.kt (commonMain)
package org.kmp.simfan.utils

import androidx.compose.ui.graphics.ImageBitmap

expect class PlatformBitmap
expect fun ImageBitmap.toPlatformBitmap(): PlatformBitmap

expect fun savePlatformBitmapToFile(
    bitmap: PlatformBitmap,
    fileName: String = "signature.png",
    context: Any? = null
): String?


expect fun ByteArray.toImageBitmap(): ImageBitmap?
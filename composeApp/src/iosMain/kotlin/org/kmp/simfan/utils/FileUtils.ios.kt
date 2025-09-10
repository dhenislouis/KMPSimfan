package org.kmp.simfan.utils

import platform.UIKit.UIImage
import platform.Foundation.*
import platform.UIKit.UIImagePNGRepresentation

fun saveUIImageToFile(uiImage: UIImage, fileName: String = "signature.png"): String? {
    val docsDir = NSSearchPathForDirectoriesInDomains(
        directory = NSDocumentDirectory,
        domainMask = NSUserDomainMask,
        expandTilde = true
    ).firstOrNull() as? String ?: return null

    val filePath = "$docsDir/$fileName"
    val fileUrl = NSURL.fileURLWithPath(filePath)

    val imageData = UIImagePNGRepresentation(uiImage) ?: return null
    imageData.writeToURL(fileUrl, true)

    return filePath
}
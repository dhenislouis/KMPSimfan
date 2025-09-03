package org.kmp.simfan.components.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.UIKit.*
import platform.Foundation.*
import platform.darwin.NSObject
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.useContents

@Composable
actual fun CameraView(
    onPhotoTaken: (ByteArray) -> Unit,
    modifier: Modifier
) {
    UIKitView(
        modifier = modifier,
        factory = {
            val picker = UIImagePickerController().apply {
                sourceType = UIImagePickerControllerSourceTypeCamera
                allowsEditing = false
                delegate = CameraDelegate(onPhotoTaken)
            }
            picker.view
        }
    )
}

@ExportObjCClass
private class CameraDelegate(
    private val onPhotoTaken: (ByteArray) -> Unit
) : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {

    @ObjCAction
    fun imagePickerController(
        picker: UIImagePickerController,
        didFinishPickingMediaWithInfo: Map<Any?, *>
    ) {
        val image = didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as? UIImage
        image?.let {
            val data = it.toNSData()
            onPhotoTaken(data.toByteArray())
        }
        picker.dismissViewControllerAnimated(true, null)
    }

    @ObjCAction
    fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, null)
    }
}

private fun UIImage.toNSData(): NSData {
    return UIImageJPEGRepresentation(this, 1.0) ?: NSData()
}

private fun NSData.toByteArray(): ByteArray {
    val bytes = ByteArray(length.toInt())
    bytes.useContents {
        (this@toByteArray.bytes as? kotlinx.cinterop.CPointer<ByteVar>)?.let { ptr ->
            for (i in 0 until length.toInt()) {
                bytes[i] = ptr[i]
            }
        }
    }
    return bytes
}

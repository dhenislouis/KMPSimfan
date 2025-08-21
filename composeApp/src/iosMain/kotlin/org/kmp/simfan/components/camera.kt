package org.kmp.simfan.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.AVFoundation.*
import platform.UIKit.*

@Composable
actual fun CameraPreview(modifier: Modifier) {
    UIKitView(
        factory = {
            val view = UIView()

            // Setup AVCaptureSession
            val session = AVCaptureSession().apply {
                sessionPreset = AVCaptureSessionPresetPhoto
            }

            // Pilih kamera belakang
            val device = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)
            val input = AVCaptureDeviceInput.deviceInputWithDevice(device, error = null)
            if (input != null && session.canAddInput(input as AVCaptureInput)) {
                session.addInput(input)
            }

            // Buat preview layer
            val previewLayer = AVCaptureVideoPreviewLayer(session).apply {
                videoGravity = AVLayerVideoGravityResizeAspectFill
                frame = view.bounds
            }
            view.layer.addSublayer(previewLayer)

            session.startRunning()
            view
        },
        modifier = modifier
    )
}

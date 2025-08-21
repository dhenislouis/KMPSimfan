package org.kmp.simfan.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CameraPreview(
    modifier: Modifier = Modifier,
    onPhotoCaptured: (ByteArray) -> Unit
)

expect suspend fun takePicture(): ByteArray?

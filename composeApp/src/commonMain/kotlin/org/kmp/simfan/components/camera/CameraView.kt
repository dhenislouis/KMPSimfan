package org.kmp.simfan.components.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CameraView(
    onPhotoTaken: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
)

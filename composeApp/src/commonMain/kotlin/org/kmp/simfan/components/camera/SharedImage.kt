package org.kmp.simfan.components.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun SharedImage(
    photoBytes: ByteArray,
    modifier: Modifier = Modifier
)

package org.kmp.simfan.components.number

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumDisplay(
    value: String,
    showValue: Boolean,
    onClick: () -> Unit,
    isOtp: Boolean = false,
    valueLength: Int
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(valueLength) { index ->
            val char = value.getOrNull(index)?.toString() ?: ""
            val animatedScale by animateFloatAsState(
                if (char.isNotEmpty()) 1f else 0.8f, label = ""
            )
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E7FF))
                    .graphicsLayer(scaleX = animatedScale, scaleY = animatedScale),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when {
                        isOtp -> char
                        showValue -> char
                        char.isNotEmpty() -> "•"
                        else -> ""
                    },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

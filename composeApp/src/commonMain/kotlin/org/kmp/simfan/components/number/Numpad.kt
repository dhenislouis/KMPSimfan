package org.kmp.simfan.components.value

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.backspace

@Composable
fun Numpad(
    value: String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit,
    maxLength: Int
) {
    val haptic = LocalHapticFeedback.current
    val buttons = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "⌫")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    if (label.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clickable {
                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    when (label) {
                                        "⌫" -> if (value.isNotEmpty()) onValueChange(value.dropLast(1))
                                        else -> if (value.length < maxLength) onValueChange(value + label)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (label == "⌫") {
                                Icon(
                                    painter = painterResource(Res.drawable.backspace),
                                    contentDescription = "Delete",
                                    tint = Color.Black,
                                    modifier = Modifier.size(36.dp)
                                )
                            } else {
                                Text(
                                    text = label,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.size(80.dp))
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onNext,
            enabled = value.length == maxLength,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
        ) {
            Text("Lanjut", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }
}
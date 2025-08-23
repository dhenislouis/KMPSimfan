package org.kmp.simfan.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kmp.simfan.core.Primary

@Composable
fun ComFilledButton(
    onClick: () -> Unit,
    text: String,
    enabled: MutableState<Boolean> = remember { mutableStateOf(true) },
    isLoading: Boolean = false,
    buttonSize: Dp = 44.dp,
    loadingSize: Dp = 24.dp,
    textSize: TextUnit = 16.sp,
    color: Color = Color(0xFFFFFFFF),
    textColor: Color = Primary,
    fontWeight: FontWeight = FontWeight.Medium,
    modifier: Modifier,
) {
    if (isLoading) {
        Box(
            modifier = modifier.height(buttonSize),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(loadingSize), color = color)
        }
    } else {
        Button(
            onClick = onClick,
            enabled = enabled.value,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(color),
            modifier = modifier
                .height(buttonSize)

        ) {
            Text(
                text = text,
                fontWeight = fontWeight,
                fontSize = textSize,
                color = textColor
            )
        }
    }
}

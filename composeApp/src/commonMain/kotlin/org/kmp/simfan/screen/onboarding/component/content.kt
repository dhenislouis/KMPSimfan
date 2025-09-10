package org.kmp.simfan.screen.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kmp.simfan.components.button.ComFilledButton

@Composable
fun ContentOnboarding(
    label: String,
    title: String,
    description: String,
    buttonText: String = "",
    onButtonClick: () -> Unit = {},
    isLoading: Boolean = false,

    ) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Label
        LabelInfo(text = label)

        Spacer(modifier = Modifier.height(12.dp)) // gap label ke text

        // Title + Description
        TextContent(title = title, description = description)

        Spacer(modifier = Modifier.height(32.dp)) // gap text ke button

        // Button

    }
}


@Composable
fun TextContent(title: String, description: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp) // Gap 16px
    ) {
        Text(
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFFFFFFF)
        )
        Text(
            text = description,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.90f)
        )
    }
}




@Composable
fun LabelInfo(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(40.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 13.sp
        )
    }
}
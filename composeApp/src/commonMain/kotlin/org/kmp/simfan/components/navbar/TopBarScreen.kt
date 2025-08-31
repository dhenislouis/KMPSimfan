package org.kmp.simfan.components.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back

@Composable
fun TopBar(title: String, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                painter = painterResource(Res.drawable.arrow_back),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
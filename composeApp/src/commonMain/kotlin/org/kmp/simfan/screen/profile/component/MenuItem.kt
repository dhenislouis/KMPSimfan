package org.kmp.simfan.screen.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_forward

@Composable
fun MenuItem(
    icon: DrawableResource,
    label: String,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(35.dp).background(Color(0xFFEFF3FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(icon), contentDescription = label, tint = Color(0xFF023FFC), modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Color(0xFF181D27))
            subtitle?.let {
                Text(it, fontSize = 11.sp, color = Color.Gray, modifier = Modifier.padding(top = 3.dp))
            }
        }
        Icon(painter = painterResource(Res.drawable.arrow_forward), contentDescription = "Chevron", tint = Color(0xFFABABAB), modifier = Modifier.size(20.dp))
    }
}

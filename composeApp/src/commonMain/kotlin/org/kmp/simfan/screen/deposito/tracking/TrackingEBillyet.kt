package org.kmp.simfan.screen.deposito.tracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TrackingTimelineScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocalShipping,
                contentDescription = "Tracking",
                tint = Color(0xFF5A5A5A),
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Tracking Timeline",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Timeline Items
        TimelineItem(
            title = "Selesai",
            description = buildAnnotatedString {
                append("E-Billyet Deposito ")
                pushStyle(SpanStyle(color = Color(0xFF2962FF), fontWeight = FontWeight.Bold))
                append("#BLY2025XXXX")
                pop()
                append(" telah dikirim ke Email user@gmail.com")
            },
            date = "Tanggal Terbit: 20/08/2025",
            isFirst = true,
            isLast = false,
            active = true
        )

        TimelineItem(
            title = "Proses",
            description = buildAnnotatedString { append("20 Agustus 2025, 14:20") },
            date = null,
            isFirst = false,
            isLast = true,
            active = false
        )
    }
}

@Composable
fun TimelineItem(
    title: String,
    description: androidx.compose.ui.text.AnnotatedString,
    date: String?,
    isFirst: Boolean,
    isLast: Boolean,
    active: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Bullet + Line
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(if (active) Color(0xFF2962FF) else Color.LightGray)
            )
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(48.dp)
                        .background(Color.LightGray)
                )
            }
        }

        // Card Content
        Card(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (active) Color(0xFF2962FF) else Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                )
                if (date != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = date,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Gray,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TrackingTimelinePreview() {
    MaterialTheme {
        Surface {
            TrackingTimelineScreen()
        }
    }
}
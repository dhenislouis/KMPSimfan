package org.kmp.simfan.screen.profile.nonaktifkanakun

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NonaktifkanAkunDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Nonaktifkan Akun",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Kamu yakin ingin nonaktifkan akun?",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5A7FFF), // biru
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(44.dp)
                    .defaultMinSize(minWidth = 120.dp)
            ) {
                Text("Nonaktifkan")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(44.dp)
                    .defaultMinSize(minWidth = 120.dp)
            ) {
                Text("Batal")
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.White
    )
}

@Preview
@Composable
fun PreviewNonaktifkanAkunDialog() {
    MaterialTheme {
        NonaktifkanAkunDialog(
            onDismiss = { println("Batal diklik") },
            onConfirm = { println("Nonaktifkan diklik") }
        )
    }
}

package org.kmp.simfan.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.painterResource
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.ic_success_illustration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessDialog(
    title: String,
    message: String,
    buttonText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(Unit) {
        sheetState.show() // Show sheet saat composable muncul
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_success_illustration),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = message,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(28.dp))

            Button(
                onClick = {
                    onConfirm()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
            ) {
                Text(buttonText, color = Color.White, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewSuccesDialog() {
    SuccessDialog(
        title = "Tanda Tangan Berhasil",
        message = "Tanda tangan digital Anda telah berhasil \ndiverifikasi oleh Privy.",
        buttonText = "lanjut",
        onConfirm = {},
        onDismiss = {}
    )
}
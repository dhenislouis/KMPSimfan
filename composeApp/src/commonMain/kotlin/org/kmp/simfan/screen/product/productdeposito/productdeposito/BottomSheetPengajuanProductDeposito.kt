package org.kmp.simfan.screen.product.productdeposito.productdeposito

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
import androidx.navigation.NavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputNominalBottomSheet(
    navController: NavController,
    currentRoute: Routes?,
    onDismiss: () -> Unit = {},
    onSave: (String) -> Unit = {}
) {
    var nominal by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nominal",
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 6.dp)
            )

            // TextField dengan state
            OutlinedTextField(
                value = nominal,
                onValueChange = { nominal = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                placeholder = {
                    Text("Rp.10.000.000", color = Color(0xFF9CA3AF))
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE5E7EB),
                    focusedBorderColor = Color(0xFF4F7DF3)
                )
            )

            Spacer(Modifier.height(28.dp))

            // Tombol
            Button(
                onClick = { onSave(nominal) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4F7DF3)
                )
            ) {
                Text(
                    text = "Simpan Nominal",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
//
//@Preview
//@Composable
//fun PreviewInputNominalBottomSheet() {
//    InputNominalBottomSheet()
//}

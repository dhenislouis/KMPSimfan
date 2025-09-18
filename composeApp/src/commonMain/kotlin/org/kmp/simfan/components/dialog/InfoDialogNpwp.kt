package org.kmp.simfan.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kmp.simfan.screen.profile.pengajuandata.npwp.OCRUiModel

//@Composable
//fun InfoDialogNpwp(
//    isShowing: Boolean,
//    data: OCRUiModel,
//    onDismissRequest: () -> Unit
//) {
//    if (isShowing) {
//        AlertDialog(
//            onDismissRequest = onDismissRequest,
//            title = {
//                Text("Hasil OCR", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//            },
//            text = {
//                Column(modifier = Modifier.fillMaxWidth()) {
//                    Text("NPWP: ${data.npwpNumber}", fontSize = 16.sp)
//                    data.confidence?.let {
//                        Spacer(Modifier.height(8.dp))
//                        Text("Confidence: ${(it * 100).toInt()}%", fontSize = 14.sp)
//                    }
//                }
//            },
//            confirmButton = {
//                TextButton(onClick = onDismissRequest) {
//                    Text("OK")
//                }
//            }
//        )
//    }
//}



import androidx.compose.ui.window.Dialog
import org.kmp.simfan.screen.profile.pengajuandata.npwp.NPWPUiModel

@Composable
fun InfoDialogNpwp(
    isShowing: Boolean,
    data: NPWPUiModel,
    onDismissRequest: () -> Unit = {},
) {
    if (isShowing) {
        Dialog(onDismissRequest = onDismissRequest) {
            // Desain dialog untuk menampilkan data NPWP
            // Sesuaikan dengan kebutuhan Anda
        }
    }
}
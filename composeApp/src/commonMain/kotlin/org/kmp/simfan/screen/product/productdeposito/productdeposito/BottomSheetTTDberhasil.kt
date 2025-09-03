package org.kmp.simfan.screen.product.productdeposito.productdeposito

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetTTDBerhasilScreen(
    navController: NavController,
    currentRoute: Routes?,
    onDismiss: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon atau ilustrasi
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFF3F6FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.ttd_berhasil),
                contentDescription = "Success Icon",
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        // Judul
        Text(
            text = "Tanda Tangan Berhasil",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF22242F)
        )

        Spacer(Modifier.height(8.dp))

        // Subjudul
        Text(
            text = "Tanda tangan digital Anda telah berhasil diverifikasi oleh Privy.",
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 12.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(Modifier.height(28.dp))

        // Tombol "Lanjutkan"
        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4F7DF3)
            )
        ) {
            Text(
                text = "Lanjutkan",
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun PreviewSignatureSuccessBottomSheet() {
//    val scope = rememberCoroutineScope()
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//
//    // Simulasi bottom sheet dibuka
//    BottomSheetTTDBerhasilScreen(
//        onDismiss = {},
//        onContinue = {}
//    )
//}

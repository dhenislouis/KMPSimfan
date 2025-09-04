package org.kmp.simfan.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_circle

@Composable
fun LoginSuccessScreen(
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Garis abu-abu di atas
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(4.dp)
                .background(Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(2.dp))
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Lingkaran besar
        Box(
            modifier = Modifier.size(240.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
                    .background(Color(0xFFEFF3FF))
            )

            // Card putih + animasi / fallback
            Column(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .shadow(6.dp, RoundedCornerShape(24.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                SuccessAnimation(modifier = Modifier.size(70.dp))

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.DarkGray)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                )
            }

            // Icon panah
            Image(
                painter = painterResource(Res.drawable.arrow_circle),
                contentDescription = "Action",
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Yey! Login Berhasil",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Anda akan segera diarahkan ke layar\nberanda. Nikmati fitur-fiturnya!",
            fontSize = 14.sp,
            color = Color.Gray,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = onContinueClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF668CFF))
        ) {
            Text(
                text = "Masuk",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Preview
@Composable
fun PreviewLoginSuccess() {
    LoginSuccessScreen(onContinueClick = {})
}

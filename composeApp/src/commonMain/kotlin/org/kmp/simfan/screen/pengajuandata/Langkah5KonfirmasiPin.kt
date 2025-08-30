package org.kmp.simfan.screen.pengajuandata

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.Label_Langkah
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.backspace
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

// 🚀 Voyager Screen
object Langkah5KonfirmasiPinScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Langkah5KonfirmasiPinUI(
            onBackClick = {navigator.pop()},
            onNext = {
                navigator.push(LangkahBerhasilScreen)
            }
        )
    }
}

@Composable
fun Langkah5KonfirmasiPinUI(
    onBackClick: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }
    var showPin by remember { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // bg_secondary
    ) {
        // 🔹 TopBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "Pengajuan Data",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            Spacer(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        // 🔹 Content
        // 🔹 Content (di atas putih)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                "Langkah 5 dari 5",
                fontSize = 11.sp,
                color = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Label_Langkah)
                    .padding(horizontal = 12.dp, vertical = 3.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Atur PIN Rekening",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                "PIN terdiri dari 6 angka dan bersifat rahasia. Jangan bagikan PIN Anda kepada siapa pun untuk menjaga keamanan akun.",
                fontSize = 12.sp,
                color = Color.Black,
                lineHeight = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }

// 🔹 Bagian Putih: PIN Display + Eye + Numpad
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🔹 Judul & Label PIN
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Konfirmasi PIN",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "Masukkan PIN",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // 🔹 PIN Display
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(6) { index ->
                    val char = pin.getOrNull(index)?.toString() ?: ""
                    val animatedScale by animateFloatAsState(
                        if (char.isNotEmpty()) 1f else 0.8f, label = ""
                    )
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE0E7FF))
                            .graphicsLayer(scaleX = animatedScale, scaleY = animatedScale),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (showPin) char else if (char.isNotEmpty()) "•" else "",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // 🔹 Eye Icon
            IconButton(onClick = { showPin = !showPin }) {
                Icon(
                    painter = painterResource(
                        if (showPin) Res.drawable.eye_off else Res.drawable.eye_on
                    ),
                    contentDescription = if (showPin) "Sembunyikan PIN" else "Tampilkan PIN",
                    tint = Color.Gray
                )
            }

            Spacer(Modifier.height(16.dp))

            // 🔹 Numpad
            val buttons = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("", "0", "⌫")
            )

            buttons.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { label ->
                        if (label.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                        when (label) {
                                            "⌫" -> if (pin.isNotEmpty()) pin = pin.dropLast(1)
                                            else -> if (pin.length < 6) pin += label
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (label == "⌫") {
                                    Icon(
                                        painter = painterResource(Res.drawable.backspace),
                                        contentDescription = "Hapus",
                                        tint = Color.Black,
                                        modifier = Modifier.size(28.dp)
                                    )
                                } else {
                                    Text(
                                        text = label,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.size(80.dp))
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Button1)
            ) {
                Text(
                    "Lanjut",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLangkah5KonfirmasiPinUI() {
    Langkah5KonfirmasiPinUI()
}

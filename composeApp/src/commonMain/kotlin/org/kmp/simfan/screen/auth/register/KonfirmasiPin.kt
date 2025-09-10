package org.kmp.simfan.screen.auth.register

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
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.backspace
import simfan.composeapp.generated.resources.eye_off
import simfan.composeapp.generated.resources.eye_on

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KonfirmasiPin(
    navController: NavController,
    currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onKonfirmasiPin: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }
    var showPin by remember { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "PIN",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF4F4F4)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    "Konfirmasi PIN",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Silahkan Konfirmasi PIN Anda",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color.White)
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(6) { index ->
                        val char = pin.getOrNull(index)?.toString() ?: ""
                        val animatedScale by animateFloatAsState(
                            if (char.isNotEmpty()) 1f else 0.9f,
                            label = ""
                        )

                        Card(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                                .graphicsLayer(
                                    scaleX = animatedScale,
                                    scaleY = animatedScale
                                ),
                            shape = CircleShape,
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (showPin) char else if (char.isNotEmpty()) "•" else "",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

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
                    onClick = onKonfirmasiPin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text(
                        "Konfirmasi PIN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewInputPinAjukanPenempatanProductDepositoScreen() {
//    KonfirmasiPin()
//}

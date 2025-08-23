package org.kmp.simfan.screen.profile.akunsaya

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPribadiScreen(
    onBackClick: () -> Unit = {},
    onAjukanPerubahan: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(if (expanded) 90f else 0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
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
                text = "Data Pribadi",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = {},
                enabled = false,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            ) {}
        }

        // 🔹 Scroll Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // Header + Toggle
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                    ) {
                        Text(
                            text = "Data Diri",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Informasi pribadi seperti nama, tempat tanggal lahir, jenis kelamin, dan kewarganegaraan.",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 20.dp)
                            )

                            Icon(
                                painter = painterResource(Res.drawable.arrow_forward),
                                contentDescription = "Toggle",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(18.dp)
                                    .rotate(rotation) // 🔹 Animasi rotasi
                            )
                        }
                    }

                    // 🔹 Animated Expandable Content
                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))

                            DataItem("Nomor KTP", "872367489012")
                            DataItem("Nomor Handphone", "0872367489012")
                            DataItem("Email", "clarasaputri@gmail.com")
                            DataItem("Nama", "Clara Saputri")
                            DataItem("Tempat Lahir", "Jakarta")
                            DataItem("Tanggal Lahir", "17 Maret 2003")
                            DataItem("Jenis Kelamin", "Perempuan")
                            DataItem("Status Perkawinan", "Cerai Mati")

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedButton(
                                onClick = onAjukanPerubahan,
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color(0xFF0066CC)
                                )
                            ) {
                                Text("Ajukan Perubahan", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DataItem(label: String, value: String) {
    Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
    Text(
        text = value,
        fontSize = 13.sp,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Preview
@Composable
fun PreviewDataPribadiScreen() {
    DataPribadiScreen()
}

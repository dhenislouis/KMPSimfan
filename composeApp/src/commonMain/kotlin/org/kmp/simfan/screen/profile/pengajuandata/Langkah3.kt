package org.kmp.simfan.screen.profile.pengajuandata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.Label_Langkah
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Langkah3Screen(
    navController: NavController, currentRoute: Routes?,
    onBack: () -> Unit = {},
    onLanjut: () -> Unit = {},
    onUploadCamera: () -> Unit = {},
    onUploadFile: () -> Unit = {}
) {
    var npwp by remember { mutableStateOf("") }
    var noNpwpChecked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Pengajuan Data",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                actions = { Spacer(Modifier.size(48.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onLanjut,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1),
                    shape = RoundedCornerShape(24.dp) // rounded sesuai gambar
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Header step
            Text(
                "Langkah 3 dari 5",
                fontSize = 11.sp,
                color = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Label_Langkah)
                    .padding(horizontal = 12.dp, vertical = 3.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Input NPWP
            Text(
                "Nomor NPWP",
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = npwp,
                onValueChange = { npwp = it },
                placeholder = { Text("00.000.000.0-000.000", fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                textStyle = TextStyle(fontSize = 13.sp, color = Color.Black),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )

            Spacer(Modifier.height(20.dp))

            // Upload Foto NPWP
            Text(
                "Upload Foto NPWP",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = onUploadCamera,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_camera),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Buka Kamera",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onUploadFile,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Pilih", fontSize = 14.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    "Tidak ada file yang dipilih",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = noNpwpChecked,
                    onCheckedChange = { noNpwpChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = Button1)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Saya belum memiliki NPWP",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewLangkah3UI() {
//    Langkah3UI()
//}

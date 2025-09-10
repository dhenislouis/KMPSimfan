package org.kmp.simfan.screen.profile.pengajuandata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.Label_Langkah
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.arrow_down

@Composable
fun Langkah4Screen(
    navController: NavController, currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onNext: () -> Unit = {}
) {
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

        // 🔹 Konten scroll
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF4F4F4))
                    .padding(16.dp)
            ) {
                Text(
                    "Langkah 4 dari 5",
                    fontSize = 11.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Label_Langkah)
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Ceritakan Lebih Banyak Tentang Dirimu",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Isilah informasi berikut agar kami bisa mempersonalisasi layanan deposito untukmu.",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            // 🔹 Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                DropdownField("Tujuan membuka rekening*", "Pilih kategori")
                DropdownField("Sumber Dana", "Pilih kategori")
                DropdownField("Pekerjaan", "Pilih kategori")
                DropdownField("Jabatan di pekerjaan", "Pilih kategori")
                DropdownField("Penghasilan Bulanan", "Pilih kategori")
                DropdownField("Sektor Industri", "Pilih kategori")

                InputField("Alamat Tempat Bekerja", "Masukkan alamat tempat kerja")
                InputField("Nomor Telepon Tempat Bekerja", "Masukkan Nomor Telepon Tempat Bekerja", KeyboardType.Phone)
                InputField("Nama Ibu Kandung", "Masukkan nama ibu kandung")
                InputField("NPWP", "Masukkan NPWP", KeyboardType.Number)
            }
        }

        // 🔹 Bottom button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Button1)
            ) {
                Text("Lanjut", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }
    }
}

@Composable
fun DropdownField(label: String, hint: String) {
    var value by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(label, fontSize = 14.sp, color = Color(0xFF505559))
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            placeholder = { Text(hint, fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.arrow_down),
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(8.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            singleLine = true
        )
    }
}

@Composable
fun InputField(label: String, hint: String, keyboardType: KeyboardType = KeyboardType.Text) {
    var value by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(label, fontSize = 14.sp, color = Color(0xFF505559))
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            placeholder = { Text(hint, fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
            shape = RoundedCornerShape(8.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

//@Preview
//@Composable
//fun PreviewLangkah4UI() {
//    Langkah4UI()
//}

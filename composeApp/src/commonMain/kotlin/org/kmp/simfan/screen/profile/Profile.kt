package org.kmp.simfan.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.core.navigation.BottomBar
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.*

@Composable
fun ProfileScreen(
    navController: NavController, currentRoute: Routes?, authManager: AuthManager
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { navController.navigate(it) }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FAFB))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Profil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                ProfileHeader()

                Spacer(modifier = Modifier.height(16.dp))

                StatusCard()

                Spacer(modifier = Modifier.height(8.dp))

                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_phone,
                        label = "Pengajuan Penempatan",
                        onClick = {
                            navController.navigate(Routes.Langkah)
                        }
                    )
                }

                Spacer(Modifier.height(8.dp))
                DividerLine()

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_profil,
                        label = "Akun Saya",
                        subtitle = "Lakukan perubahan pada akun anda",
                        onClick = {
                            navController.navigate(Routes.AkunSaya)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_lock,
                        label = "Ubah Kata Sandi",
                        subtitle = "Ganti kata sandi untuk keamanan akun",
                        onClick = {
                            navController.navigate(Routes.UbahPassword)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_credit,
                        label = "Akun Bank",
                        subtitle = "Akun ini berfungsi sebagai tujuan pencairan\nbunga, pokok deposito, dan transaksi cashback.",
                        onClick = {
                            navController.navigate(Routes.AkunBank)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_ttd,
                        label = "Akun Tanda Tangan Elektronik",
                        subtitle = "Perbarui data tanda tangan digital",
                        onClick = {
                            navController.navigate(Routes.TandaTanganElektronik)
                        }
                    )
                }

                Spacer(Modifier.height(8.dp))
                DividerLine()

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_user_add,
                        label = "Undang Teman & Dapatkan Bonus",
                        onClick = {
//                            navController.navigate(Routes.UndangTeman)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_money,
                        label = "Riwayat Promo & Cashback",
                        onClick = {
//                            navController.navigate(Routes.RiwayatPromoCashback)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_gift,
                        label = "Keuntungan Eksklusif Komunal",
                        onClick = {
//                            navController.navigate(Routes.KeuntunganEkslusifKomunal)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_chartbar,
                        label = "History Poin Privileges / Riwayat Poin Hadiah",
                        onClick = {
//                            navController.navigate(Routes.HistoryPoin)
                        }
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Informasi & Bantuan",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_dokumen,
                        label = "Ketentuan Layanan",
                        onClick = {
                            navController.navigate(Routes.KetentuanLayanan)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_shieldplus,
                        label = "Aturan Privasi",
                        onClick = {
//                            navController.navigate(Routes.AturanPrivasi)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_question,
                        label = "Pertanyaan Umum",
                        onClick = {
                            navController.navigate(Routes.PertanyaanUmum)
                        }
                    )
                    MenuItem(
                        icon = Res.drawable.ic_building,
                        label = "Profil Komunal / Info Komunal",
                        onClick = {
//                            navController.navigate(Routes.ProInKomunal)
                        }
                    )
                }

                Spacer(Modifier.height(8.dp))
                DividerLine()

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_trash,
                        label = "Nonaktifkan Akun",
                        onClick = {
                            navController.navigate(Routes.NonAktifkanAkun)
                        }
                    )
                }

                Spacer(Modifier.height(8.dp))
                DividerLine()

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_signout,
                        label = "Keluar dari Aplikasi",
                        onClick = {
                            authManager.logout()
                            navController.navigate(Routes.Onboard) {
                                popUpTo(Routes.Home) { inclusive = true }
                                launchSingleTop = true
                            }
//                            navController.navigate(Routes.KeluarAplikasi)
                        }
                    )
                }

                Spacer(Modifier.height(8.dp))
                DividerLine()

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_handscoin,
                        label = "Advisor Keuangan",
                        onClick = {
//                            navController.navigate(Routes.AdvisorKeuangan)
                        }
                    )
                }

                Spacer(Modifier.height(8.dp))
                DividerLine()

                Spacer(Modifier.height(8.dp))
                SectionCard {
                    MenuItem(
                        icon = Res.drawable.ic_headset,
                        label = "Bantuan tersedia?",
                        onClick = {
//                            navController.navigate(Routes.Bantuan)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun ProfileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(Res.drawable.ayu_cantika),
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("Ayu Cantika", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("ayucantika@email.com", fontSize = 11.sp, color = Color.Gray)
        }
        ReferralBox()
    }
}

@Composable
fun ReferralBox() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color(0xFFEFF3FF), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_kodereveral),
            contentDescription = "Referral",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Kode Referral", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = Color(0xFF181D27))
            Text("RV07162", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF181D27))
        }
    }
}

@Composable
fun StatusCard(
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9E2FF)),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_king),
                        contentDescription = "Gold",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Bronze",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF181D27),
                        modifier = Modifier.padding(start = 6.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Text(
                            text = "0 Poin",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "Poin akan hangus pada",
                        fontSize = 8.sp,
                        color = Color(0xFF181D27)
                    )
                    Text(
                        text = " 10 Jan 2025",
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF181D27)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        progress = { 0.75f },
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp),
                        color = Color(0xFF4A68FF),
                        trackColor = Color(0xFFE0E0E0)
                    )
                    Icon(
                        painter = painterResource(Res.drawable.ic_king),
                        contentDescription = "progress_icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 5.dp)
                            .size(15.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Transaksi Berjalan:",
                            fontSize = 8.sp,
                            color = Color(0xFF181D27)
                        )
                        Text(
                            text = " Rp1.34M",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF181D27),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Text(
                        text = "Rp1.34M",
                        fontSize = 7.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF181D27),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFB0C3FE))
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dengan ini saya menyetujui ketentuan layanan",
                    fontSize = 8.sp,
                    color = Color(0xFF22242F),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow_forward),
                    contentDescription = "next",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(width = 10.dp, height = 13.dp)
                )
            }
        }
    }
}



@Composable
fun SectionCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
            content()
        }
    }
}

@Composable
fun MenuItem(
    icon: DrawableResource,
    label: String,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .background(Color(0xFFEFF3FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(resource  = icon),
                contentDescription = label,
                tint = Color(0xFF023FFC),
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Color(0xFF181D27))
            if (subtitle != null) {
                Text(
                    subtitle,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
        }
        Icon(
            painter = painterResource(Res.drawable.arrow_forward),
            contentDescription = "Chevron",
            tint = Color(0xFFABABAB),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun DividerLine() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE5E7EB))
            .padding(vertical = 8.dp)
    )
}

//@Preview()
//@Composable
//fun ProfilePreview() {
//    ProfileScreen()
//}
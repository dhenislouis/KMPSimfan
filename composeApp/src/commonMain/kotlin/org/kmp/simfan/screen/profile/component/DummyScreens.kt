package org.kmp.simfan.screen.profile.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

// Helper screen biar gampang
open class SimpleScreen(private val title: String) : Screen {
    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 🔹 Daftar target screen
object PengajuanPenempatanScreen : SimpleScreen("Pengajuan Penempatan")
object AkunSayaScreen : SimpleScreen("Akun Saya")
object UbahPasswordScreen : SimpleScreen("Ubah Kata Sandi")
object AkunBankScreen : SimpleScreen("Akun Bank")
object AkunTTDScreen : SimpleScreen("Akun TTD")
object UndangTemanScreen : SimpleScreen("Undang Teman")
object RiwayatPromoScreen : SimpleScreen("Riwayat Promo")
object KeuntunganEksklusifScreen : SimpleScreen("Keuntungan Eksklusif")
object HistoryPoinScreen : SimpleScreen("History Poin")
object KetentuanScreen : SimpleScreen("Ketentuan Layanan")
object AturanPrivasiScreen : SimpleScreen("Aturan Privasi")
object FAQScreen : SimpleScreen("FAQ")
object ProfilKomunalScreen : SimpleScreen("Profil Komunal")
object NonaktifkanAkunScreen : SimpleScreen("Nonaktifkan Akun")
object LogoutScreen : SimpleScreen("Logout")
object AdvisorKeuanganScreen : SimpleScreen("Advisor Keuangan")
object BantuanScreen : SimpleScreen("Bantuan")

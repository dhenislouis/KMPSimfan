package org.kmp.simfan



import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Splash : Routes

    // OnBoarding
    @Serializable
    data object  Onboard: Routes
    @Serializable
    data object Onboard1 : Routes
    @Serializable
    data object Onboard2 : Routes
    @Serializable
    data object Onboard3 : Routes
    @Serializable
    data object Onboard4 : Routes



    // Auth
    @Serializable
    data object Login : Routes
    @Serializable
    data object LoginSuccess : Routes
    @Serializable
    data object LoginSyaratKetentuan : Routes
    @Serializable
    data object Register : Routes
    @Serializable
    data object RegisterVerifikasi : Routes
    @Serializable
    data object BuatPin : Routes
    @Serializable
    data object KonfirmasiPin : Routes
    @Serializable
    data object LupaPassword : Routes
    @Serializable
    data object VerifikasiSMS : Routes
    @Serializable
    data object VerifikasiEmail : Routes
    @Serializable
    data object NewPassword : Routes



    @Serializable
    data object Home : Routes
    @Serializable
    data object Product : Routes
    @Serializable
    data object Simfanku : Routes
    @Serializable
    data object SimpanankuDeposito : Routes
    @Serializable
    data object SimpanankuTabungan : Routes
    @Serializable
    data object Profile : Routes




    // Sub Page Home
    @Serializable
    data object Notification : Routes
    @Serializable
    data object Bantuan : Routes
    @Serializable
    data object Promo : Routes




    // Sub Page Produk
    @Serializable
    data object ProductDeposito : Routes

    @Serializable
    data object ProductTabungan : Routes

    @Serializable
    data object DetailBPRProductDeposito : Routes
    @Serializable
    data object DetailProductDeposito : Routes

    @Serializable
    data object PengajuanProductDeposito : Routes

    @Serializable
    data object BottomSheetPengajuanProductDeposito : Routes

    @Serializable
    data object AjukanPenempatanProductDeposito : Routes

    @Serializable
    data object SyaratKetentuanProductDeposito : Routes
    @Serializable
    data object TandaTanganProductDeposito : Routes

    @Serializable
    data object InputPinAjukanPenempatanProductDeposito : Routes

    @Serializable
    data object BottomSheetTTDBerhasil : Routes

    @Serializable
    data object BerhasilPenempatanProductDeposito : Routes
    @Serializable
    data object FilterDeposito : Routes

    @Serializable
    data object FilterTabungan : Routes
    @Serializable
    data object DetailProductTabungan : Routes
    @Serializable
    data object DetailBPRProductTabungan : Routes




    //Sub Page Simpananku
    //Deposito
    @Serializable
    data object DetailDepositoSimpananku : Routes
    @Serializable
    data object TrackingBilyetFisikDepositoSimpananku : Routes
    @Serializable
    data object TrackingEBilyetDepositoSimpananku : Routes
    @Serializable
    data object TrackingDetailStatusDepositoSimpananku : Routes

    //Tabungan
    @Serializable
    data object DetailTabunganSimpananku : Routes




    // Sub Page Profile
    @Serializable
    data object Langkah : Routes
    @Serializable
    data object Langkah1 : Routes
    @Serializable
    data object Langkah1KTP : Routes
    @Serializable
    data object Langkah2Panduan : Routes
    @Serializable
    data object Langkah2Verifikasi : Routes
    @Serializable
    data object Langkah3 : Routes
    @Serializable
    data object Langkah4 : Routes
    @Serializable
    data object Langkah5BuatPin : Routes
    @Serializable
    data object Langkah5KonfirmasiPin : Routes
    @Serializable
    data object PengajuanDataBerhasil : Routes
    @Serializable
    data object AkunSaya : Routes
    @Serializable
    data object UbahPassword : Routes
    @Serializable
    data object AkunBank : Routes
    @Serializable
    data object TambahAkunBank : Routes
    @Serializable
    data object TandaTanganElektronik : Routes
    @Serializable
    data object KetentuanLayanan : Routes
    @Serializable
    data object PertanyaanUmum : Routes
    @Serializable
    data object NonAktifkanAkun : Routes
    @Serializable
    data object KeluarAplikasi : Routes


}
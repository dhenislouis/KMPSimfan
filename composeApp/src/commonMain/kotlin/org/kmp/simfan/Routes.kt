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
    data object DetailProductDeposito : Routes
    @Serializable
    data object SubDetailProductDeposito : Routes
    @Serializable
    data object DetailProductTabungan : Routes
    @Serializable
    data object DetailBpr : Routes

    //Sub Page Simfanku
    @Serializable
    data object  SimfankuDeposito : Routes
    @Serializable
    data object SimfankuTabungan : Routes
    @Serializable
    data object DetailDeposito : Routes
    @Serializable
    data object DetailTabungan : Routes

    // Sub Page Profile

}
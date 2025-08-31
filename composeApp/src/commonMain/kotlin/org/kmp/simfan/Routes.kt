package org.kmp.simfan



import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Splash : Routes

    @Serializable
    data object Onboard1 : Routes
    @Serializable
    data object Onboard2 : Routes
    @Serializable
    data object Onboard3 : Routes
    @Serializable
    data object Onboard4 : Routes
    @Serializable
    data object Home : Routes
    @Serializable
    data object Product : Routes

    @Serializable
    data object ProductDetail : Routes

    @Serializable
    data object BPRDetail : Routes

    @Serializable
    data object DepositoDetail : Routes

    @Serializable
    data object Simfanku : Routes
    @Serializable
    data object Profile : Routes

    @Serializable
    data object SyaratKetentuan : Routes

    @Serializable
    data object TandaTangan : Routes

    @Serializable
    data object InputPin : Routes

    @Serializable
    data object OTP : Routes

}
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
}
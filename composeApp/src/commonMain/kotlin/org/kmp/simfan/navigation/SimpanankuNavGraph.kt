package org.kmp.simfan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.simfananku.*
import org.kmp.simfan.screen.simfananku.detail.detailtabungan.*
import org.kmp.simfan.screen.simfananku.depositosimpananku.*

fun NavGraphBuilder.simpanankuGraph(navController: NavController) {
    //DEPOSITO
    composable<Routes.Simfanku> {
        SimfankuScreen(
            navController = navController,
            currentRoute = Routes.Simfanku,
            onDetailDepositoSimfanku = { navController.navigate(Routes.DetailDepositoSimpananku) },
            onDetailTabunganSimfanku = { navController.navigate(Routes.SimpanankuTabungan) }
        )
    }
    composable<Routes.DetailDepositoSimpananku> {
        val trackingDataDetailDeposito = listOf(
            TrackingStatusDetailDeposito("Pengiriman Billyet", "Tandatangani dokumen untuk persetujuan BPR"),
            TrackingStatusDetailDeposito("Penyetoran Dana", "Dana sudah diterima oleh BPR"),
            TrackingStatusDetailDeposito("Menandatangani Dokumen", "Dokumen sudah ditandatangani"),
            TrackingStatusDetailDeposito("Menunggu persetujuan BPR", "Proses validasi oleh BPR")
        )

        DetailDepositoSimpanankuScreen(
            navController = navController,
            currentRoute = Routes.DetailDepositoSimpananku,
            onBackClick = { navController.navigate(Routes.Simfanku) },
            onLihatDetail = { navController.navigate(Routes.TrackingDetailStatusDepositoSimpananku) },
            onLacakPengirimanBillyet = { navController.navigate(Routes.TrackingBilyetFisikDepositoSimpananku) },
            statusList = trackingDataDetailDeposito,
            currentStep = 0
        )
    }

    composable<Routes.TrackingDetailStatusDepositoSimpananku> {
        TrackingDetailStatusScreen(
            navController = navController,
            currentRoute = Routes.TrackingDetailStatusDepositoSimpananku,
            onClose = { navController.navigate(Routes.DetailDepositoSimpananku) }
        )
    }
    composable<Routes.TrackingBilyetFisikDepositoSimpananku> {
        TrackingBilyetFisikScreen(
            navController = navController,
            currentRoute = Routes.TrackingBilyetFisikDepositoSimpananku,
            onClose = { navController.navigate(Routes.DetailDepositoSimpananku) }
        )
    }

    //TABUNGAN
    composable<Routes.SimpanankuTabungan> {
        SimfankuTabunganScreen(
            onDetailTabunganSimfanku = { navController.navigate(Routes.DetailTabunganSimpananku) }
        )
    }
    composable<Routes.DetailTabunganSimpananku> {
        DetailTabunganScreen(
            navController = navController,
            currentRoute = Routes.DetailTabunganSimpananku,
            onBackClick = { navController.navigate(Routes.SimpanankuTabungan) },
            onTandaTangan = { navController.navigate(Routes.DetailTabunganSimpananku) }
        )
    }
}
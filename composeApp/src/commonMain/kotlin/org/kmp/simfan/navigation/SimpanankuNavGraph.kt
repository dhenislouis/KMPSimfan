package org.kmp.simfan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.simfananku.*
import org.kmp.simfan.screen.simfananku.depositosimpananku.*
import org.kmp.simfan.screen.simfananku.tabungansimpfananku.*

fun NavGraphBuilder.simpanankuGraph(navController: NavController) {
    //DEPOSITO
    composable<Routes.Simfanku> {
        SimfankuScreen(
            navController = navController,
            currentRoute = Routes.Simfanku,
            onDetailDepositoSimfanku = { navController.navigate(Routes.DetailDepositoSimpananku) },
            onDetailTabunganSimfanku = { navController.navigate(Routes.DetailTabunganSimpananku) }
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
        TrackingDetailStatusDeposito(
            navController = navController,
            currentRoute = Routes.TrackingDetailStatusDepositoSimpananku,
            onClose = { navController.navigate(Routes.DetailDepositoSimpananku) }
        )
    }
    composable<Routes.TrackingBilyetFisikDepositoSimpananku> {
        TrackingBilyetFisikDeposito(
            navController = navController,
            currentRoute = Routes.TrackingBilyetFisikDepositoSimpananku,
            onClose = { navController.navigate(Routes.DetailDepositoSimpananku) }
        )
    }

    //TABUNGAN
    composable<Routes.DetailTabunganSimpananku> {
        val trackingDataDetailTabungan = listOf(
            TrackingStatusDetailDeposito("Pengiriman Billyet", "Tandatangani dokumen untuk persetujuan BPR"),
            TrackingStatusDetailDeposito("Penyetoran Dana", "Dana sudah diterima oleh BPR"),
            TrackingStatusDetailDeposito("Menandatangani Dokumen", "Dokumen sudah ditandatangani"),
            TrackingStatusDetailDeposito("Menunggu persetujuan BPR", "Proses validasi oleh BPR")
        )

        DetailTabunganSimpanankuScreen(
            navController = navController,
            currentRoute = Routes.DetailTabunganSimpananku,
            onBackClick = { navController.navigate(Routes.Simfanku) },
            onLihatDetail = { navController.navigate(Routes.TrackingDetailStatusTabunganSimpananku) },
            onTambahPenempatan = { navController.navigate(Routes.Product) },
            onAjukanPencairan = { navController.navigate(Routes.InputPinTabunganSimpananku) },
            statusList = trackingDataDetailTabungan,
            currentStep = 0
        )
    }
    composable<Routes.TrackingDetailStatusTabunganSimpananku> {
        TrackingDetailStatusTabungan(
            navController = navController,
            currentRoute = Routes.TrackingDetailStatusTabunganSimpananku,
            onClose = { navController.navigate(Routes.DetailTabunganSimpananku) }
        )
    }
    composable<Routes.InputPinTabunganSimpananku> {
        InputPinTabunganSimpanankuScreen(
            navController = navController,
            currentRoute = Routes.InputPinTabunganSimpananku,
            onBackClick = { navController.navigate(Routes.DetailTabunganSimpananku) },
            onLanjutClick = { navController.navigate(Routes.PenempatanTabunganBerhasil) }
        )
    }
    composable<Routes.PenempatanTabunganBerhasil> {
        PenempatanTabunganSimpanankuBerhasilScreen(
            navController = navController,
            currentRoute = Routes.InputPinTabunganSimpananku,
            onKembaliBeranda = { navController.navigate(Routes.Home) },
            onDetail = { navController.navigate(Routes.TrackingStatusPencairanTabungan) }
        )
    }
    composable<Routes.TrackingStatusPencairanTabungan> {
        TrackingPencairanTabungan(
            navController = navController,
            currentRoute = Routes.InputPinTabunganSimpananku,
            onClose = { navController.navigate(Routes.Simfanku) }
        )
    }
}
package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.profile.*
import org.kmp.simfan.screen.profile.akunbank.*
import org.kmp.simfan.screen.profile.akunsaya.*
import org.kmp.simfan.screen.profile.pengajuandata.*
import org.kmp.simfan.screen.profile.pertanyaanumum.*
import org.kmp.simfan.screen.profile.syaratketentuan.*

@Composable
expect fun TandaTanganElektronikRoute(navController: NavHostController)

fun NavGraphBuilder.profileGraph(navController: NavController) {
    composable<Routes.Profile> {
        ProfileScreen(navController, Routes.Profile)
    }
    composable<Routes.Langkah> {
        LangkahScreen(
            navController = navController,
            currentRoute = Routes.Langkah,
            onBack = { navController.navigate(Routes.Profile) },
            onLanjut = { navController.navigate(Routes.Langkah1) }
        )
    }
    composable<Routes.Langkah1> {
        Langkah1Screen(
            navController = navController,
            currentRoute = Routes.Langkah1,
            onBack = { navController.navigate(Routes.Langkah) },
            onAmbilFoto = { navController.navigate(Routes.Langkah2Panduan) }
        )
    }
    composable<Routes.Langkah1KTP> {
        Langkah1Screen(
            navController = navController,
            currentRoute = Routes.Langkah1KTP,
            onBack = { navController.navigate(Routes.Langkah1) },
            onAmbilFoto = { navController.navigate(Routes.Langkah2Panduan) }
        )
    }
    composable<Routes.Langkah2Panduan> {
        Langkah2PanduanScreen(
            navController = navController,
            currentRoute = Routes.Langkah2Panduan,
            onBack = { navController.navigate(Routes.Langkah1) },
            onScanWajah = { navController.navigate(Routes.Langkah3) }
        )
    }
    composable<Routes.Langkah3> {
        Langkah3Screen(
            navController = navController,
            currentRoute = Routes.Langkah3,
            onBack = { navController.navigate(Routes.Langkah2Panduan) },
            onLanjut = { navController.navigate(Routes.Langkah4) }
        )
    }
    composable<Routes.Langkah4> {
        Langkah4Screen(
            navController = navController,
            currentRoute = Routes.Langkah4,
            onBackClick = { navController.navigate(Routes.Langkah3) },
            onNext = { navController.navigate(Routes.Langkah5BuatPin) }
        )
    }
    composable<Routes.Langkah5BuatPin> {
        Langkah5InputPinScreen(
            navController = navController,
            currentRoute = Routes.Langkah5BuatPin,
            onBackClick = { navController.navigate(Routes.Langkah4) },
            onNext = { navController.navigate(Routes.PengajuanDataBerhasil) }
        )
    }
    composable<Routes.PengajuanDataBerhasil> {
        LangkahBerhasilScreen(
            navController = navController,
            currentRoute = Routes.PengajuanDataBerhasil,
            onMulaiClick = { navController.navigate(Routes.Product) }
        )
    }
    composable<Routes.AkunSaya> {
        DataPribadiScreen(
            navController = navController,
            currentRoute = Routes.AkunSaya,
            onBackClick = { navController.navigate(Routes.Profile) },
            onAjukanClick = { navController.navigate(Routes.Profile) }
        )
    }
    composable<Routes.AkunBank> {
        AkunBankScreen(
            navController = navController,
            currentRoute = Routes.AkunBank,
            onBackClick = { navController.navigate(Routes.Profile) },
            onTambahAkunClick = { navController.navigate(Routes.TambahAkunBank) }
        )
    }
    composable<Routes.TambahAkunBank> {
        TambahBankScreen(
            navController = navController,
            currentRoute = Routes.TambahAkunBank,
            onBackClick = { navController.navigate(Routes.AkunBank) },
            onTambahBank = { navController.navigate(Routes.AkunBank) }
        )
    }
    composable<Routes.TandaTanganElektronik> {
        TandaTanganElektronikRoute(navController as NavHostController)
    }

    composable<Routes.KetentuanLayanan> {
        SyaratKetentuanScreen(
            navController = navController,
            currentRoute = Routes.KetentuanLayanan,
            onBack = { navController.navigate(Routes.Profile) }
        )
    }

    composable<Routes.PertanyaanUmum> {
        PertanyaanUmumScreen(
            navController = navController,
            currentRoute = Routes.KetentuanLayanan,
            onBackClick = { navController.navigate(Routes.Profile) }
        )
    }
}
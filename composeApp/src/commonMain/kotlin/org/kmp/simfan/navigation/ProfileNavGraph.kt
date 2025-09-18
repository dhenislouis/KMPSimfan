package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.profile.*
import org.kmp.simfan.screen.profile.akunbank.*
import org.kmp.simfan.screen.profile.akunsaya.*
import org.kmp.simfan.screen.profile.pengajuandata.*
import org.kmp.simfan.screen.profile.pengajuandata.ktp.KtpViewModel
import org.kmp.simfan.screen.profile.pengajuandata.npwp.NpwpViewModel
import org.kmp.simfan.screen.profile.pertanyaanumum.*
import org.kmp.simfan.screen.profile.syaratketentuan.*
import org.koin.compose.viewmodel.koinViewModel
import kotlin.text.get
import kotlin.text.set

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
            onAmbilFoto = { navController.navigate(Routes.Langkah1KTP) }
        )
    }
    composable<Routes.Langkah1KTP> {
        val viewModel = koinViewModel<KtpViewModel>()
        val state by viewModel.state.collectAsState()

        Langkah1KTPScreen(
            navController = navController,
            currentRoute = Routes.Langkah1KTP,
            state = state,
            onBackPressed = { navController.navigate(Routes.Langkah) },
            onCapture = { byteArray ->
                // simpan ke SavedStateHandle
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("ktp_image", byteArray)

                // navigasi ke preview
                navController.navigate(Routes.Langkah1KTPPreview)
            },
            onClear = { viewModel.onClear() }
        )
    }
    composable<Routes.Langkah1KTPPreview> { backStackEntry ->
        val byteArray = backStackEntry.savedStateHandle.get<ByteArray>("ktp_image")
        KtpPreviewScreen(
            navController = navController,
            imageData = byteArray ?: ByteArray(0)
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
            onLanjut = { navController.navigate(Routes.Langkah4) },
            onUploadCamera = { navController.navigate(Routes.Langkah3NPWP) }
        )
    }
    composable<Routes.Langkah3NPWP> {
        val viewModel = koinViewModel<NpwpViewModel>()
        val state by viewModel.state.collectAsState()

        Langkah3NPWPScreen(
            navController = navController,
            currentRoute = Routes.Langkah3NPWP,
            state = state,
            onBackPressed = { navController.navigate(Routes.Langkah3) },
            onCapture = { byteArray ->
                // simpan ke SavedStateHandle
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("npwp_image", byteArray)

                // navigasi ke preview
                navController.navigate(Routes.Langkah3NPWPPreview)
            },
            onClear = { viewModel.onClear() }
        )
    }
    composable<Routes.Langkah3NPWPPreview> { backStackEntry ->
        val byteArray = backStackEntry.savedStateHandle.get<ByteArray>("npwp_image")
        NpwpPreviewScreen(
            navController = navController,
            imageData = byteArray ?: ByteArray(0)
        )
    }
//    composable<Routes.Langkah3NPWPCamera> {
//        NpwpCameraScreen(
//            onCapture = { byteArray ->
//                navController.currentBackStackEntry
//                    ?.savedStateHandle
//                    ?.set("npwp_image", byteArray)
//
//                navController.navigate(Routes.Langkah3NPWPPreview)
//            }
//        )
//    }
//    composable<Routes.Langkah3NPWPPreview> { backStackEntry ->
//        val viewModel = koinViewModel<NpwpViewModel>()
//        val state by viewModel.state.collectAsState()
//        val byteArray = backStackEntry.savedStateHandle.get<ByteArray>("npwp_image")
//
//        NpwpPreviewScreen(
//            navController = navController,
//            imageData = byteArray ?: ByteArray(0),
//            state = state,
//            onGunakanFoto = { img -> viewModel.getOcr(img) },
//            onClear = { viewModel.onClear() },
//            onSuccess = { navController.navigate(Routes.Langkah4) }
//        )
//    }

    composable<Routes.Langkah4> {
        Langkah4Screen(
            navController = navController,
            currentRoute = Routes.Langkah4,
            onBackClick = { navController.navigate(Routes.Langkah3) },
            onNext = { navController.navigate(Routes.Langkah5InputPin) }
        )
    }
    composable<Routes.Langkah5InputPin> {
        Langkah5InputPinScreen(
            navController = navController,
            currentRoute = Routes.Langkah5InputPin,
            onBackClick = { navController.navigate(Routes.Langkah4) },
            onNext = { navController.navigate(Routes.Langkah5KonfirmasiPin) }
        )
    }
    composable<Routes.Langkah5KonfirmasiPin> {
        Langkah5KonfirmasiPinScreen(
            navController = navController,
            currentRoute = Routes.Langkah5KonfirmasiPin,
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
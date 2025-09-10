package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.product.filter.*
import org.kmp.simfan.screen.product.productdeposito.*
import org.kmp.simfan.screen.product.productdeposito.productdeposito.*
import org.kmp.simfan.screen.product.producttabungan.*

@Composable
expect fun SyaratKetentuanProductDepositoRoute(navController: NavHostController)

@Composable
expect fun TandaTanganProductDepositoRoute(navController: NavHostController)

fun NavGraphBuilder.productGraph(navController: NavController) {
    // PRODUK DEPOSITO
    composable<Routes.Product> {
        ProductDepositoScreen(
            navController = navController,
            currentRoute = Routes.Product,
            onScreenDeposito = { navController.navigate(Routes.Product) },
            onScreenTabungan = { navController.navigate(Routes.ProductTabungan) },
            onDetail = { navController.navigate(Routes.DetailProductDeposito) },
            onAjukanPenempatan = { navController.navigate(Routes.PengajuanProductDeposito) }
        )
    }
    composable<Routes.DetailProductDeposito> {
        DetailProductDeposito(
            navController = navController,
            currentRoute = Routes.DetailProductDeposito,
            onBackClick = { navController.navigate(Routes.Product) }
        )
    }
    composable<Routes.PengajuanProductDeposito> {
        PengajuanProductDepositoScreen(
            navController = navController,
            currentRoute = Routes.PengajuanProductDeposito,
            onBackClick = { navController.navigate(Routes.Product) },
            onDetailProdukLainnya = { navController.navigate(Routes.PengajuanProductDeposito) }
        )
    }
    composable<Routes.BottomSheetPengajuanProductDeposito> {
        InputNominalBottomSheet(
            navController = navController,
            currentRoute = Routes.BottomSheetPengajuanProductDeposito,
            onSave = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
            onDismiss = { navController.navigate(Routes.PengajuanProductDeposito) }
        )
    }
    composable<Routes.AjukanPenempatanProductDeposito> {
        AjukanPenempatanProdukDepositoScreen(
            navController = navController,
            currentRoute = Routes.AjukanPenempatanProductDeposito,
            onBackClick = { navController.navigate(Routes.PengajuanProductDeposito) },
            onAjukanClick = { navController.navigate(Routes.SyaratKetentuanProductDeposito) }
        )
    }
//    composable<Routes.SyaratKetentuanProductDeposito> {
//        SyaratKetentuanProdukDepositoScreen(
//            navController = navController,
//            currentRoute = Routes.SyaratKetentuanProductDeposito,
//            onBack = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
//            onContinue = { navController.navigate(Routes.TandaTanganProductDeposito) }
//        )
//    }
    composable<Routes.SyaratKetentuanProductDeposito> {
        SyaratKetentuanProductDepositoRoute(navController as NavHostController)
    }
    composable<Routes.TandaTanganProductDeposito> {
        TandaTanganProductDepositoRoute(navController as NavHostController)
    }
    composable<Routes.InputPinAjukanPenempatanProductDeposito> {
        InputPinAjukanPenempatanProductDepositoScreen(
            navController = navController,
            currentRoute = Routes.InputPinAjukanPenempatanProductDeposito,
            onBackClick = { navController.navigate(Routes.AjukanPenempatanProductDeposito) }
        )
    }
    composable<Routes.BottomSheetTTDBerhasil> {
        BottomSheetTTDBerhasilScreen(
            navController = navController,
            currentRoute = Routes.BottomSheetTTDBerhasil,
            onDismiss = { navController.navigate(Routes.BottomSheetTTDBerhasil) },
            onContinue = { navController.navigate(Routes.BerhasilPenempatanProductDeposito) }
        )
    }
    composable<Routes.BerhasilPenempatanProductDeposito> {
        PenempatanDepositoBerhasilScreen(
            navController = navController,
            currentRoute = Routes.BerhasilPenempatanProductDeposito,
            onBack = { navController.navigate(Routes.ProductDeposito) },
            onLihatSimpanan = { navController.navigate(Routes.SimpanankuDeposito) },
            onKembaliBeranda = { navController.navigate(Routes.Home) }
        )
    }

    // PRODUK TABUNGAN
    composable<Routes.ProductTabungan> {
        ProductTabunganScreen(
            navController = navController,
            currentRoute = Routes.ProductTabungan,
            onScreenDeposito = { navController.navigate(Routes.Product) },
            onScreenTabungan = { navController.navigate(Routes.ProductTabungan) }
        )
    }
}
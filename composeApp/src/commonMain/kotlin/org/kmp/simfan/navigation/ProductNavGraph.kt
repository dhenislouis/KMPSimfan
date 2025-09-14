package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.product.ProductScreen
import org.kmp.simfan.screen.product.filter.FilterScreen
import org.kmp.simfan.screen.product.productdeposito.*
import org.kmp.simfan.screen.product.producttabungan.DetailBprTabunganScreen
import org.kmp.simfan.screen.product.producttabungan.DetailTabunganScreen

@Composable
expect fun SyaratKetentuanProductDepositoRoute(navController: NavHostController)

@Composable
expect fun TandaTanganProductDepositoRoute(navController: NavHostController)

fun NavGraphBuilder.productGraph(navController: NavController) {
    // PRODUK DEPOSITO
    composable<Routes.Product> {
        ProductScreen(
            navController = navController,
            currentRoute = Routes.Product,
            onFilterDeposito = { navController.navigate(Routes.FilterDeposito) },
            onFilterTabungan = { navController.navigate(Routes.FilterTabungan) },
            onDetailBprDeposito = { navController.navigate(Routes.DetailBPRProductDeposito) },
            onDetailBprTabungan = { navController.navigate(Routes.DetailBPRProductTabungan) },
            onDetailDepositoClick = { navController.navigate(Routes.DetailProductDeposito) },
            onDetailTabunganClick = { navController.navigate(Routes.DetailProductTabungan) }
        )
    }
    composable<Routes.DetailBPRProductDeposito> {
        DetailBPRProductDeposito(
            navController = navController,
            currentRoute = Routes.DetailBPRProductDeposito,
            onBackClick = { navController.navigate(Routes.Product) }
        )
    }
    composable<Routes.DetailProductDeposito> {
        DetailProductDepositoScreen(
            navController = navController,
            currentRoute = Routes.DetailProductDeposito,
            onBackClick = { navController.navigate(Routes.Product) },
            onDetailProdukLainnya = { navController.navigate(Routes.Product) }
        )
    }
    composable<Routes.BottomSheetPengajuanProductDeposito> {
        InputNominalBottomSheet(
            navController = navController,
            currentRoute = Routes.BottomSheetPengajuanProductDeposito,
            onSave = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
            onDismiss = { navController.navigate(Routes.DetailProductDeposito) }
        )
    }
    composable<Routes.AjukanPenempatanProductDeposito> {
        AjukanPenempatanProdukDepositoScreen(
            navController = navController,
            currentRoute = Routes.AjukanPenempatanProductDeposito,
            onBackClick = { navController.navigate(Routes.DetailProductDeposito) },
            onAjukanClick = { navController.navigate(Routes.SyaratKetentuanProductDeposito) }
        )
    }
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
    composable<Routes.FilterDeposito> {
        FilterScreen(
            navController = navController,
            currentRoute = Routes.FilterDeposito,
            onClose = { navController.navigate(Routes.Product) }
        )
    }

    // PRODUK TABUNGAN
    composable<Routes.DetailBPRProductTabungan> {
        DetailBprTabunganScreen("Simfan WebSuite", subtitle = "DKI Jakarta - 3 Transaksi", navController = navController)
    }
    composable<Routes.DetailProductTabungan> {
        DetailTabunganScreen(
            onDetailClick = {navController.navigate(Routes.DetailProductTabungan)},
            onDetailBprClick = {navController.navigate(Routes.DetailBPRProductTabungan)},
            navController = navController
        )
    }
    composable<Routes.FilterTabungan> {
        FilterScreen(
            navController = navController,
            currentRoute = Routes.FilterTabungan,
            onClose = { navController.navigate(Routes.Product) }
        )
    }
}
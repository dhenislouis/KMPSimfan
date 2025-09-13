package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.product.ProductScreen
import org.kmp.simfan.screen.product.productdeposito.*

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
            onFilterDeposito = { navController.navigate(Routes.DetailBPRProductDeposito) },
            onFilterTabungan = { navController.navigate(Routes.DetailProductDeposito) },
            onDetailBprDeposito = { navController.navigate(Routes.DetailProductDeposito) },
            onDetailBprTabungan = { navController.navigate(Routes.DetailProductDeposito) },
            onDetailDepositoClick = { navController.navigate(Routes.DetailProductDeposito) },
            onDetailTabunganClick = { navController.navigate(Routes.PengajuanProductDeposito) }
        )
    }
    composable<Routes.DetailBPRProductDeposito> {
        DetailBPRProductDeposito(
            navController = navController,
            currentRoute = Routes.DetailProductDeposito,
            onBackClick = { navController.navigate(Routes.Product) }
        )
    }
    composable<Routes.DetailProductDeposito> {
        DetailProductDepositoScreen(
            navController = navController,
            currentRoute = Routes.DetailProductDeposito,
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

}
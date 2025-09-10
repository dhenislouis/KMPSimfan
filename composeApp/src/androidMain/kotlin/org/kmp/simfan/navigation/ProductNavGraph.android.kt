package org.kmp.simfan.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.product.productdeposito.productdeposito.SyaratKetentuanProdukDepositoScreen
import org.kmp.simfan.screen.profile.tandatangan.TandaTanganScreen
import org.kmp.simfan.utils.savePlatformBitmapToFile
import org.kmp.simfan.utils.toPlatformBitmap
import java.io.File

fun isSignatureSaved(context: Context): Boolean {
    val file = File(context.filesDir, "signature.png")
    return file.exists()
}

@Composable
actual fun SyaratKetentuanProductDepositoRoute(navController: NavHostController) {
    val context = LocalContext.current

    SyaratKetentuanProdukDepositoScreen(
        navController = navController,
        currentRoute = Routes.SyaratKetentuanProductDeposito,
        onBack = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
        onContinue = {
            if (isSignatureSaved(context)) {
                // ✅ jika tanda tangan sudah ada
                navController.navigate(Routes.InputPinAjukanPenempatanProductDeposito)
            } else {
                // ❌ jika tanda tangan belum ada
                navController.navigate(Routes.TandaTanganProductDeposito)
            }
        }
    )
}

@Composable
actual fun TandaTanganProductDepositoRoute(navController: NavHostController) {
    val context = LocalContext.current

    TandaTanganScreen(
        navController = navController,
        currentRoute = Routes.TandaTanganElektronik,
        onBackClick = { navController.navigate(Routes.SyaratKetentuanProductDeposito) },
        onSave = { imageBitmap ->
            val platformBitmap = imageBitmap.toPlatformBitmap()
            savePlatformBitmapToFile(platformBitmap, "signature.png", context)

            // ✅ langsung ke Input PIN
            navController.navigate(Routes.InputPinAjukanPenempatanProductDeposito) {
                popUpTo(Routes.SyaratKetentuanProductDeposito) { inclusive = true }
            }
        }
    )
}

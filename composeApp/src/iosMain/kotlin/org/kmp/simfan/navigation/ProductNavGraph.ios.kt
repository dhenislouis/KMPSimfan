package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.cinterop.ExperimentalForeignApi
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.profile.tandatangan.TandaTanganScreen
import org.kmp.simfan.utils.savePlatformBitmapToFile
import org.kmp.simfan.utils.toPlatformBitmap
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class)
fun isSignatureSaved(): Boolean {
    val paths = NSSearchPathForDirectoriesInDomains(
        directory = NSDocumentDirectory,
        domainMask = NSUserDomainMask,
        expandTilde = true
    )
    val documentsDirectory = paths.firstOrNull() as? String ?: return false
    val filePath = "$documentsDirectory/signature.png"
    return NSFileManager.defaultManager.fileExistsAtPath(filePath)
}

@Composable
actual fun SyaratKetentuanProductDepositoRoute(navController: NavHostController) {
    SyaratKetentuanProdukDepositoScreen(
        navController = navController,
        currentRoute = Routes.SyaratKetentuanProductDeposito,
        onBack = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
        onContinue = {
            if (isSignatureSaved()) {
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
    TandaTanganScreen(
        navController = navController,
        currentRoute = Routes.TambahAkunBank,
        onBackClick = { navController.navigate(Routes.AkunBank) },
        onSave = { imageBitmap ->
            val platformBitmap = imageBitmap.toPlatformBitmap()
            val path = savePlatformBitmapToFile(platformBitmap, "signature.png")
            println("✅ iOS: File tanda tangan tersimpan di: $path")

            navController.navigate(Routes.InputPinAjukanPenempatanProductDeposito) {
                popUpTo(Routes.SyaratKetentuanProductDeposito) { inclusive = true }
            }
        }
    )
}
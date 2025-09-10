// ProfileNavGraph.ios.kt
package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.profile.tandatangan.TandaTanganScreen
import org.kmp.simfan.utils.toPlatformBitmap
import org.kmp.simfan.utils.savePlatformBitmapToFile

@Composable
actual fun TandaTanganElektronikRoute(navController: NavHostController) {
    TandaTanganScreen(
        navController = navController,
        currentRoute = Routes.TambahAkunBank,
        onBackClick = { navController.navigate(Routes.AkunBank) },
        onSave = { imageBitmap ->
            val platformBitmap = imageBitmap.toPlatformBitmap()
            val path = savePlatformBitmapToFile(platformBitmap, "signature.png")
            println("✅ iOS: File tanda tangan tersimpan di: $path")

            navController.navigate(Routes.Profile) {
                popUpTo(Routes.Profile) { inclusive = true }
            }
        }
    )
}

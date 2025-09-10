// ProfileNavGraph.android.kt
package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.profile.tandatangan.TandaTanganScreen
import org.kmp.simfan.utils.toPlatformBitmap
import org.kmp.simfan.utils.savePlatformBitmapToFile

@Composable
actual fun TandaTanganElektronikRoute(navController: NavHostController) {
    val context = LocalContext.current

    TandaTanganScreen(
        navController = navController,
        currentRoute = Routes.TambahAkunBank,
        onBackClick = { navController.navigate(Routes.AkunBank) },
        onSave = { imageBitmap ->
            val platformBitmap = imageBitmap.toPlatformBitmap()
            val path = savePlatformBitmapToFile(platformBitmap, "signature.png", context)
            println("✅ Android: File tanda tangan tersimpan di: $path")

            navController.navigate(Routes.Profile) {
                popUpTo(Routes.Profile) { inclusive = true }
            }
        }
    )
}
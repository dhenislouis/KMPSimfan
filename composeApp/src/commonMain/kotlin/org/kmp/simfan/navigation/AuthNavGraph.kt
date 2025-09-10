package org.kmp.simfan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.auth.login.*
import org.kmp.simfan.screen.auth.password.*
import org.kmp.simfan.screen.auth.register.*

fun NavGraphBuilder.authGraph(navController: NavController) {
    composable<Routes.Login> {
        LoginScreenUI(
            navController = navController,
            currentRoute = Routes.Login,
//                        onLoginClick = { navController.navigate(Routes.LoginSuccess) },
            onForgotPasswordClick = { navController.navigate(Routes.Login) },
            onRegisterClick = { navController.navigate(Routes.Register) }
        )
    }
    composable<Routes.LoginSuccess> {
        LoginSuccessScreen(
            navController = navController,
            currentRoute = Routes.LoginSuccess,
            onDismiss = { navController.navigate(Routes.LoginSuccess) },
            onContinueClick = { navController.navigate(Routes.LoginSyaratKetentuan) }
        )
    }
    composable<Routes.LoginSyaratKetentuan> {
        LoginSyaratKetentuanUI(
            onContinue = { navController.navigate(Routes.Home) }
        )
    }


    composable<Routes.Register> {
        RegisterScreenUI(
            navController = navController,
            currentRoute = Routes.Login,
            onBackClick = { navController.navigate(Routes.Login) },
//                        onRegisterClick = { navController.navigate(Routes.Login) },
            onGoogleLoginClick = { navController.navigate(Routes.Register) }
        )
    }
    composable<Routes.RegisterVerifikasi> {
        RegisterVerifikasiBottomSheet(
            navController = navController,
            currentRoute = Routes.RegisterVerifikasi,
            onDismiss = { navController.navigate(Routes.RegisterVerifikasi) },
            onSave = { navController.navigate(Routes.BuatPin) }
        )
    }
    composable<Routes.BuatPin> {
        BuatPin(
            navController = navController,
            currentRoute = Routes.BuatPin,
            onBuatPIN = { navController.navigate(Routes.KonfirmasiPin) }
        )
    }
    composable<Routes.KonfirmasiPin> {
        KonfirmasiPin(
            navController = navController,
            currentRoute = Routes.KonfirmasiPin,
            onBackClick = { navController.navigate(Routes.BuatPin) },
            onKonfirmasiPin = { navController.navigate(Routes.Login) }
        )
    }


    composable<Routes.NewPassword> {
        NewPasswordUI(
            onBackClick = { navController.navigate(Routes.NewPassword) },
            onConfirmClick = { navController.navigate(Routes.Login) }
        )
    }
}
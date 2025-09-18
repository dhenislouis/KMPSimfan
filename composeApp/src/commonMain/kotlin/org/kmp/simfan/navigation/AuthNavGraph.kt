package org.kmp.simfan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.model.FirebaseTokenRequest
import org.kmp.simfan.screen.auth.login.*
import org.kmp.simfan.screen.auth.password.*
import org.kmp.simfan.screen.auth.register.*

fun NavGraphBuilder.authGraph(
    navController: NavController,
    loginWithGoogle: suspend () -> FirebaseTokenRequest
) {
    composable<Routes.Login> {
        LoginScreenUI(
            navController = navController,
            currentRoute = Routes.Login,
            onBackClick = { navController.popBackStack() },
            onForgotPasswordClick = { navController.navigate(Routes.LupaPassword) },
            onRegisterClick = { navController.navigate(Routes.Register) },
            loginWithGoogle = loginWithGoogle
        )
    }
    composable<Routes.LoginSuccess> {
        LoginSuccessScreen(
            navController = navController,
            currentRoute = Routes.LoginSuccess,
            onDismiss = { navController.popBackStack() },
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
            onBackClick = { navController.popBackStack() },
            onGoogleLoginClick = { /* Handle Google login */ }
        )
    }
    composable<Routes.RegisterVerifikasi> {
        RegisterVerifikasiBottomSheet(
            navController = navController,
            currentRoute = Routes.RegisterVerifikasi,
            onDismiss = { navController.popBackStack() },
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
            onBackClick = { navController.popBackStack() },
            onKonfirmasiPin = { navController.navigate(Routes.Login) }
        )
    }
    composable<Routes.LupaPassword> {
        LupaPasswordScreenUI(
            navController = navController,
            onBackClick = { navController.popBackStack() },
            onNextClick = { method ->
                when (method) {
                    "SMS" -> navController.navigate(Routes.VerifikasiSMS)
                    "Email" -> navController.navigate(Routes.VerifikasiEmail)
                    else -> navController.navigate(Routes.VerifikasiSMS)
                }
            }
        )
    }

    composable<Routes.VerifikasiSMS> {
        VerifikasiSMSUI(
            phoneNumber = "081234567899",
            navController = navController,
            onBackClick = { navController.popBackStack() },
            onResendClick = { /* Handle resend */ },
            onVerifyClick = { navController.navigate(Routes.NewPassword) }
        )
    }

    composable<Routes.VerifikasiEmail> {
        VerifikasiEmailScreenUI(
            email = "user@example.com",
            navController = navController,
            onBackClick = { navController.popBackStack() },
            onResendClick = { /* Handle resend */ },
            onVerifyClick = { navController.navigate(Routes.NewPassword) }
        )
    }

    composable<Routes.NewPassword> {
        NewPasswordUI(
            navController = navController,
            onBackClick = { navController.popBackStack() },
            onConfirmClick = { navController.navigate(Routes.Login) }
        )
    }

    composable<Routes.UbahPassword> {
        UbahPasswordUI(
            navController = navController,
            onBackClick = { navController.popBackStack() },
            onSaveClick = { navController.popBackStack() }
        )
    }
}
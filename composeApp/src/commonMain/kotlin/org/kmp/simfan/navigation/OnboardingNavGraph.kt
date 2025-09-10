package org.kmp.simfan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.onboarding.*

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    composable<Routes.Onboard> {
        OnboardingScreen(
            onFinished = {
                navController.navigate(Routes.Onboard1) {
                    popUpTo(Routes.Onboard) { inclusive = true }
                }
            }
        )
    }
    composable<Routes.Onboard1> {
        OnboardingStep1(onNextClick = { navController.navigate(Routes.Onboard2) })
    }
    composable<Routes.Onboard2> {
        OnboardingStep2(onNextClick = { navController.navigate(Routes.Onboard3) })
    }
    composable<Routes.Onboard3> {
        OnboardingStep3(onNextClick = { navController.navigate(Routes.Onboard4) })
    }
    composable<Routes.Onboard4> {
        OnboardingStep4(onNextClick = { navController.navigate(Routes.Login) })
    }
}

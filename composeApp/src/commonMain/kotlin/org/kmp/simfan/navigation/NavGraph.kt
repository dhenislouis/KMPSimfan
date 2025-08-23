package org.kmp.simfan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(startDestination: String = "step1") {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("step1") {
            OnboardingStep1(
                onNextClick = { navController.navigate("step2") }
            )
        }
        composable("step2") {
            OnboardingStep2(
                onNextClick = { navController.navigate("step3") }
            )
        }
        composable("step3") {
            OnboardingStep3(
                onNextClick = { /* pindah ke home */ }
            )
        }
    }
}

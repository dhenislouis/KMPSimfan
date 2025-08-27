package org.kmp.simfan

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.core.Theme
import org.kmp.simfan.core.navigation.BottomBar
import org.kmp.simfan.screen.deposito.DepositoScreen
import org.kmp.simfan.screen.home.HomeScreen
import org.kmp.simfan.screen.onboarding.OnboardingStep1
import org.kmp.simfan.screen.onboarding.OnboardingStep2
import org.kmp.simfan.screen.onboarding.OnboardingStep3
import org.kmp.simfan.screen.onboarding.OnboardingStep4
import org.kmp.simfan.screen.product.ProductScreen
import org.kmp.simfan.screen.profile.ProfileScreen

//@Composable
//@Preview
//fun App() {
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(
//            modifier = Modifier
//                .background(MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
//}

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route
    Theme {

        Scaffold(

        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.Onboard1,

                ) {
                composable<Routes.Onboard1> {
                    OnboardingStep1(
                        onNextClick = { navController.navigate(Routes.Onboard2) }
                    )
                }
                composable<Routes.Onboard2> {
                    OnboardingStep2(
                        onNextClick = { navController.navigate(Routes.Onboard3) }
                    )
                }
                composable<Routes.Onboard3> {
                    OnboardingStep3(
                        onNextClick = { navController.navigate(Routes.Onboard4) }
                    )
                }
                composable<Routes.Onboard4> {
                    OnboardingStep4(
                        onNextClick = { navController.navigate(Routes.Home) }
                    )
                }
                composable<Routes.Home> {
                    HomeScreen(navController, Routes.Home)
                }
                composable<Routes.Product> {
                    ProductScreen(navController, Routes.Product)
                }
                composable<Routes.Simfanku> {
                    DepositoScreen(navController, Routes.Simfanku)
                }
                composable<Routes.Profile> {
                    ProfileScreen(navController, Routes.Profile)
                }
//            composable<Routes.Product> {
//                ProductScreen()
//            }
//            composable<Routes.Deposito> {
//                DepositoScreen()
//            }
//            composable<Routes.Profile> {
//                ProfileScreen()
//            }
            }
        }
    }
}
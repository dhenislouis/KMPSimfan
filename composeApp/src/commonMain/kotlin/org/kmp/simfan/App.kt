package org.kmp.simfan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.screen.home.HomeScreen
import org.kmp.simfan.screen.onboarding.OnboardingStep1
import org.kmp.simfan.screen.onboarding.OnboardingStep2
import org.kmp.simfan.screen.onboarding.OnboardingStep3
import org.kmp.simfan.screen.onboarding.OnboardingStep4

import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.compose_multiplatform

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
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.Onboard1
        ) {
            // Onboarding tanpa AppBar
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
                HomeScreen()
            }

            // Contoh screen lain yang pakai AppBar
//            composable<Routes.Home> {
//                Scaffold(
//                    topBar = {
//                        TopAppBar(
//                            title = { Text("Home") }
//                        )
//                    }
//                ) { padding ->
//                    HomeScreen(Modifier.padding(padding))
//                }
//            }
        }
    }
}
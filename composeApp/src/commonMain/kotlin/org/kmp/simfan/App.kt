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
import org.kmp.simfan.screen.home.HomeScreen
import org.kmp.simfan.screen.onboarding.OnboardingStep1
import org.kmp.simfan.screen.onboarding.OnboardingStep2
import org.kmp.simfan.screen.onboarding.OnboardingStep3
import org.kmp.simfan.screen.onboarding.OnboardingStep4
import org.kmp.simfan.screen.profile.ProfileScreen
import org.kmp.simfan.screen.auth.login.LoginScreenUI
import org.kmp.simfan.screen.auth.login.LoginSuccessScreen
import org.kmp.simfan.screen.auth.login.LoginSyaratKetentuanUI
import org.kmp.simfan.screen.auth.password.NewPasswordUI
import org.kmp.simfan.screen.auth.password.VerifikasiEmailScreenUI
import org.kmp.simfan.screen.auth.password.VerifikasiSMSUI
import org.kmp.simfan.screen.auth.register.RegisterScreenUI
import org.kmp.simfan.screen.deposito.SimfankuDepositoScreen
import org.kmp.simfan.screen.deposito.SimfankuTabunganScreen
import org.kmp.simfan.screen.deposito.detail.detaildeposito.DetailDepositoScreen
import org.kmp.simfan.screen.deposito.detail.detailtabungan.DetailTabunganScreen
import org.kmp.simfan.screen.home.notification.NotificationScreen
import org.kmp.simfan.screen.home.promo.PromoScreen
import org.kmp.simfan.screen.onboarding.OnboardingScreen
import org.kmp.simfan.screen.product.ProductDepositoScreen
import org.kmp.simfan.screen.product.ProductTabunganScreen
import org.kmp.simfan.screen.product.detail.DetailScreen
import org.kmp.simfan.screen.product.detail.subdetail.SubDetailScreen

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
                startDestination = Routes.Onboard,

                ) {
                composable<Routes.Onboard> {
                    OnboardingScreen(
                        onFinished = {
                            navController.navigate(Routes.Home) {
                                popUpTo(Routes.Onboard) { inclusive = true }
                            }
                        }
                    )
                }
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
                        onNextClick = { navController.navigate(Routes.Login) }
                    )
                }
                composable<Routes.Login> {
                    LoginScreenUI(
                    onLoginClick = { navController.navigate(Routes.LoginSuccess) },
                    onForgotPasswordClick = { navController.navigate(Routes.Login) },
                    onRegisterClick = { navController.navigate(Routes.Register) }
                    )
                }
                composable<Routes.LoginSuccess> {
                    LoginSuccessScreen(
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
                        onBackClick = { navController.navigate(Routes.Login) },
                        onRegisterClick = { navController.navigate(Routes.Login) },
                        onGoogleLoginClick = { navController.navigate(Routes.Register) }
                    )
                }
                composable<Routes.NewPassword> {
                    NewPasswordUI(
                        onBackClick = { navController.navigate(Routes.NewPassword) },
                        onConfirmClick = { navController.navigate(Routes.Login) }
                    )
                }

                composable<Routes.Home> {
                    HomeScreen(
                        navController = navController,
                        currentRoute = Routes.Home,
                        onScreenNotification = { navController.navigate(Routes.Notification) },
                        onScreenPromo = { navController.navigate(Routes.Promo) }
                    )
                }

                composable<Routes.Notification> {
                    NotificationScreen(
                        navController = navController,
                        currentRoute = Routes.Notification,
                        onCloseClick = { navController.navigate(Routes.Home) }
                    )
                }

                composable<Routes.Promo> {
                    PromoScreen(
                        navController = navController,
                        currentRoute = Routes.Home,
                        onBackClick = { navController.navigate(Routes.Home) },
                        onMenuClick = { navController.navigate(Routes.Promo) }
                    )
                }


                composable<Routes.Product> {
                    ProductDepositoScreen(
                        navController = navController,
                        currentRoute = Routes.Product,
                        onScreenDeposito = { navController.navigate(Routes.Product) },
                        onScreenTabungan = { navController.navigate(Routes.ProductTabungan) },
                        AjukanPenempatan = { navController.navigate(Routes.DetailProductDeposito) }
                    )
                }

                composable<Routes.DetailProductDeposito> {
                    DetailScreen(
                        navController = navController,
                        currentRoute = Routes.DetailProductDeposito,
                        onBackClick = { navController.navigate(Routes.Product) },
                        onAjukanClick = { navController.navigate(Routes.DetailProductDeposito) },
                        DetailProdukLainnya = { navController.navigate(Routes.SubDetailProductDeposito) }
                    )
                }

                composable<Routes.SubDetailProductDeposito> {
                    SubDetailScreen(
                        navController = navController,
                        currentRoute = Routes.SubDetailProductDeposito,
                        onBackClick = { navController.navigate(Routes.DetailProductDeposito) }
                    )
                }

                composable<Routes.ProductTabungan> {
                    ProductTabunganScreen(
                        navController = navController,
                        currentRoute = Routes.ProductTabungan,
                        onScreenDeposito = { navController.navigate(Routes.Product) },
                        onScreenTabungan = { navController.navigate(Routes.ProductTabungan) }
                    )
                }


                composable<Routes.Simfanku> {
                    SimfankuDepositoScreen(
                        navController = navController,
                        currentRoute = Routes.SimfankuDeposito,
                        onScreenDeposito = { navController.navigate(Routes.Simfanku) },
                        onScreenTabungan = { navController.navigate(Routes.SimfankuTabungan) },
                        onDetailDepositoSimfanku = { navController.navigate(Routes.DetailDeposito) }
                    )
                }

                composable<Routes.DetailDeposito> {
                    DetailDepositoScreen(
                        navController = navController,
                        currentRoute = Routes.SimfankuDeposito,
                        onBackClick = { navController.navigate(Routes.Simfanku) },
                        onTandaTangan = { navController.navigate(Routes.SimfankuTabungan) }
                    )
                }

                composable<Routes.SimfankuTabungan> {
                    SimfankuTabunganScreen(
                        navController = navController,
                        currentRoute = Routes.SimfankuTabungan,
                        onScreenDeposito = { navController.navigate(Routes.Simfanku) },
                        onScreenTabungan = { navController.navigate(Routes.SimfankuTabungan) },
                        onDetailTabunganSimfanku = { navController.navigate(Routes.DetailTabungan) }
                    )
                }

                composable<Routes.DetailTabungan> {
                    DetailTabunganScreen(
                        navController = navController,
                        currentRoute = Routes.SimfankuDeposito,
                        onBackClick = { navController.navigate(Routes.SimfankuTabungan) },
                        onTandaTangan = { navController.navigate(Routes.DetailTabungan) }
                    )
                }

                composable<Routes.Profile> {
                    ProfileScreen(navController, Routes.Profile)
                }



            }
        }
    }
}
package org.kmp.simfan

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.core.Theme
import org.kmp.simfan.model.FirebaseTokenRequest
import org.kmp.simfan.navigation.*

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
fun App(
    loginWithGoogle: suspend () -> FirebaseTokenRequest,
    authManager: AuthManager
) {
    val navController = rememberNavController()

    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route
    val startDestination = remember {
        if (authManager.isLoggedIn()) Routes.Home else Routes.Onboard
    }
    Theme {

        Scaffold(

        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.Langkah4,

                ) {

                onboardingGraph(navController)
                authGraph(navController, loginWithGoogle)
                homeGraph(navController)
                productGraph(navController)
                simpanankuGraph(navController)
                profileGraph(navController, authManager)

                // ONBOARDING
//                composable<Routes.Onboard1> {
//                    OnboardingStep1(
//                        onNextClick = { navController.navigate(Routes.Onboard2) }
//                    )
//                }
//                composable<Routes.Onboard2> {
//                    OnboardingStep2(
//                        onNextClick = { navController.navigate(Routes.Onboard3) }
//                    )
//                }
//                composable<Routes.Onboard3> {
//                    OnboardingStep3(
//                        onNextClick = { navController.navigate(Routes.Onboard4) }
//                    )
//                }
//                composable<Routes.Onboard4> {
//                    OnboardingStep4(
//                        onNextClick = { navController.navigate(Routes.Login) }
//                    )
//                }
//
//
//
//                //AUTH
//                composable<Routes.Login> {
//                    LoginScreenUI(
//                        navController = navController,
//                        currentRoute = Routes.Login,
////                        onLoginClick = { navController.navigate(Routes.LoginSuccess) },
//                        onForgotPasswordClick = { navController.navigate(Routes.Login) },
//                        onRegisterClick = { navController.navigate(Routes.Register) }
//                    )
//                }
//                composable<Routes.LoginSuccess> {
//                    LoginSuccessScreen(
//                        navController = navController,
//                        currentRoute = Routes.LoginSuccess,
//                        onDismiss = { navController.navigate(Routes.LoginSuccess) },
//                        onContinueClick = { navController.navigate(Routes.LoginSyaratKetentuan) }
//                    )
//                }
//                composable<Routes.LoginSyaratKetentuan> {
//                    LoginSyaratKetentuanUI(
//                        onContinue = { navController.navigate(Routes.Home) }
//                    )
//                }
//                composable<Routes.Register> {
//                    RegisterScreenUI(
//                        navController = navController,
//                        currentRoute = Routes.Login,
//                        onBackClick = { navController.navigate(Routes.Login) },
////                        onRegisterClick = { navController.navigate(Routes.Login) },
//                        onGoogleLoginClick = { navController.navigate(Routes.Register) }
//                    )
//                }
//                composable<Routes.RegisterVerifikasi> {
//                    RegisterVerifikasiBottomSheet(
//                        navController = navController,
//                        currentRoute = Routes.RegisterVerifikasi,
//                        onDismiss = { navController.navigate(Routes.RegisterVerifikasi) },
//                        onSave = { navController.navigate(Routes.Login) }
//                    )
//                }
//                composable<Routes.NewPassword> {
//                    NewPasswordUI(
//                        onBackClick = { navController.navigate(Routes.NewPassword) },
//                        onConfirmClick = { navController.navigate(Routes.Login) }
//                    )
//                }
//
//
//
//                // HOME
//                composable<Routes.Home> {
//                    HomeScreen(
//                        navController = navController,
//                        currentRoute = Routes.Home,
//                        onScreenNotification = { navController.navigate(Routes.Notification) },
//                        onScreenPromo = { navController.navigate(Routes.Promo) }
//                    )
//                }
//                composable<Routes.Notification> {
//                    NotificationScreen(
//                        navController = navController,
//                        currentRoute = Routes.Notification,
//                        onCloseClick = { navController.navigate(Routes.Home) }
//                    )
//                }
//                composable<Routes.Promo> {
//                    PromoScreen(
//                        navController = navController,
//                        currentRoute = Routes.Home,
//                        onBackClick = { navController.navigate(Routes.Home) },
//                        onMenuClick = { navController.navigate(Routes.Promo) }
//                    )
//                }
//
//
//
//                // PRODUK
//                // PRODUK DEPOSITO
//                composable<Routes.Product> {
//                    ProductDepositoScreen(
//                        navController = navController,
//                        currentRoute = Routes.Product,
//                        onScreenDeposito = { navController.navigate(Routes.Product) },
//                        onScreenTabungan = { navController.navigate(Routes.ProductTabungan) },
//                        onAjukanPenempatan = { navController.navigate(Routes.PengajuanProductDeposito) }
//                    )
//                }
//                composable<Routes.PengajuanProductDeposito> {
//                    PengajuanProductDepositoScreen(
//                        navController = navController,
//                        currentRoute = Routes.PengajuanProductDeposito,
//                        onBackClick = { navController.navigate(Routes.ProductDeposito) },
//                        onDetailProdukLainnya = { navController.navigate(Routes.DetailProductDeposito) }
//                    )
//                }
//                composable<Routes.DetailProductDeposito> {
//                    DetailProductDeposito(
//                        navController = navController,
//                        currentRoute = Routes.DetailProductDeposito,
//                        onBackClick = { navController.navigate(Routes.PengajuanProductDeposito) }
//                    )
//                }
//                composable<Routes.BottomSheetPengajuanProductDeposito> {
//                    InputNominalBottomSheet(
//                        navController = navController,
//                        currentRoute = Routes.BottomSheetPengajuanProductDeposito,
//                        onSave = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
//                        onDismiss = { navController.navigate(Routes.PengajuanProductDeposito) }
//                    )
//                }
//                composable<Routes.AjukanPenempatanProductDeposito> {
//                    AjukanPenempatanProdukDepositoScreen(
//                        navController = navController,
//                        currentRoute = Routes.AjukanPenempatanProductDeposito,
//                        onBackClick = { navController.navigate(Routes.PengajuanProductDeposito) },
//                        onAjukanClick = { navController.navigate(Routes.SyaratKetentuanProductDeposito) }
//                    )
//                }
//                composable<Routes.SyaratKetentuanProductDeposito> {
//                    SyaratKetentuanProdukDepositoScreen(
//                        navController = navController,
//                        currentRoute = Routes.SyaratKetentuanProductDeposito,
//                        onBack = { navController.navigate(Routes.AjukanPenempatanProductDeposito) },
//                        onContinue = { navController.navigate(Routes.InputPinAjukanPenempatanProductDeposito) }
//                    )
//                }
//                composable<Routes.InputPinAjukanPenempatanProductDeposito> {
//                    InputPinAjukanPenempatanProductDepositoScreen(
//                        navController = navController,
//                        currentRoute = Routes.InputPinAjukanPenempatanProductDeposito,
//                        onBackClick = { navController.navigate(Routes.AjukanPenempatanProductDeposito) }
////                        onNext = { navController.navigate(Routes.SubDetailProductDeposito) }
//                    )
//                }
//                composable<Routes.BottomSheetTTDBerhasil> {
//                    BottomSheetTTDBerhasilScreen(
//                        navController = navController,
//                        currentRoute = Routes.BottomSheetTTDBerhasil,
//                        onDismiss = { navController.navigate(Routes.BottomSheetTTDBerhasil) },
//                        onContinue = { navController.navigate(Routes.BerhasilPenempatanProductDeposito) }
//                    )
//                }
//                composable<Routes.BerhasilPenempatanProductDeposito> {
//                    PenempatanDepositoBerhasilScreen(
//                        navController = navController,
//                        currentRoute = Routes.BerhasilPenempatanProductDeposito,
//                        onBack = { navController.navigate(Routes.ProductDeposito) },
//                        onKembaliBeranda = { navController.navigate(Routes.Home) }
//                    )
//                }
//
//                // PRODUK TABUNGAN
//                composable<Routes.ProductTabungan> {
//                    ProductTabunganScreen(
//                        navController = navController,
//                        currentRoute = Routes.ProductTabungan,
//                        onScreenDeposito = { navController.navigate(Routes.Product) },
//                        onScreenTabungan = { navController.navigate(Routes.ProductTabungan) }
//                    )
//                }
//
//
//
//                //SIMPANANKU
//                //DEPOSITO
//                composable<Routes.SimpanankuDeposito> {
//                    SimpanankuDepositoScreen(
//                        navController = navController,
//                        currentRoute = Routes.SimpanankuDeposito,
//                        onScreenDeposito = { navController.navigate(Routes.SimpanankuDeposito) },
//                        onScreenTabungan = { navController.navigate(Routes.SimpanankuTabungan) },
//                        onDetailDepositoSimfanku = { navController.navigate(Routes.DetailDepositoSimpananku) }
//                    )
//                }
//                composable<Routes.DetailDepositoSimpananku> {
//                    val trackingDataDetailDeposito = listOf(
//                        TrackingStatusDetailDeposito("Pengiriman Billyet", "Tandatangani dokumen untuk persetujuan BPR"),
//                        TrackingStatusDetailDeposito("Penyetoran Dana", "Dana sudah diterima oleh BPR"),
//                        TrackingStatusDetailDeposito("Menandatangani Dokumen", "Dokumen sudah ditandatangani"),
//                        TrackingStatusDetailDeposito("Menunggu persetujuan BPR", "Proses validasi oleh BPR")
//                    )
//
//                    DetailDepositoSimpanankuScreen(
//                        navController = navController,
//                        currentRoute = Routes.DetailDepositoSimpananku,
//                        onBackClick = { navController.navigate(Routes.SimpanankuDeposito) },
//                        onLihatDetail = { navController.navigate(Routes.TrackingDetailStatusDepositoSimpananku) },
//                        onLacakPengirimanBillyet = { navController.navigate(Routes.TrackingBilyetFisikDepositoSimpananku) },
//                        statusList = trackingDataDetailDeposito,
//                        currentStep = 0
//                    )
//                }
//                composable<Routes.TrackingDetailStatusDepositoSimpananku> {
//                    TrackingDetailStatusScreen(
//                        navController = navController,
//                        currentRoute = Routes.TrackingDetailStatusDepositoSimpananku,
//                        onClose = { navController.navigate(Routes.DetailDepositoSimpananku) }
//                    )
//                }
//                composable<Routes.TrackingBilyetFisikDepositoSimpananku> {
//                    TrackingBilyetFisikScreen(
//                        navController = navController,
//                        currentRoute = Routes.TrackingBilyetFisikDepositoSimpananku,
//                        onClose = { navController.navigate(Routes.DetailDepositoSimpananku) }
//                    )
//                }
//
//                //TABUNGAN
//                composable<Routes.SimpanankuTabungan> {
//                    SimfankuTabunganScreen(
//                        navController = navController,
//                        currentRoute = Routes.SimpanankuTabungan,
//                        onScreenDeposito = { navController.navigate(Routes.SimpanankuDeposito) },
//                        onScreenTabungan = { navController.navigate(Routes.SimpanankuTabungan) },
//                        onDetailTabunganSimfanku = { navController.navigate(Routes.DetailTabunganSimpananku) }
//                    )
//                }
//                composable<Routes.DetailTabunganSimpananku> {
//                    DetailTabunganScreen(
//                        navController = navController,
//                        currentRoute = Routes.DetailTabunganSimpananku,
//                        onBackClick = { navController.navigate(Routes.SimpanankuTabungan) },
//                        onTandaTangan = { navController.navigate(Routes.DetailTabunganSimpananku) }
//                    )
//                }
//
//
//
//                //PROFILE
//                composable<Routes.Profile> {
//                    ProfileScreen(navController, Routes.Profile)
//                }
//                composable<Routes.Langkah> {
//                    LangkahScreen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah,
//                        onBack = { navController.navigate(Routes.Profile) },
//                        onLanjut = { navController.navigate(Routes.Langkah1) }
//                    )
//                }
//                composable<Routes.Langkah1> {
//                    Langkah1Screen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah1,
//                        onBack = { navController.navigate(Routes.Langkah) },
//                        onAmbilFoto = { navController.navigate(Routes.Langkah2Panduan) }
//                    )
//                }
//                composable<Routes.Langkah1KTP> {
//                    Langkah1Screen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah1KTP,
//                        onBack = { navController.navigate(Routes.Langkah1) },
//                        onAmbilFoto = { navController.navigate(Routes.Langkah2Panduan) }
//                    )
//                }
//                composable<Routes.Langkah2Panduan> {
//                    Langkah2PanduanScreen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah2Panduan,
//                        onBack = { navController.navigate(Routes.Langkah1) },
//                        onScanWajah = { navController.navigate(Routes.Langkah3) }
//                    )
//                }
//                composable<Routes.Langkah3> {
//                    Langkah3Screen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah3,
//                        onBack = { navController.navigate(Routes.Langkah2Panduan) },
//                        onLanjut = { navController.navigate(Routes.Langkah4) }
//                    )
//                }
//                composable<Routes.Langkah4> {
//                    Langkah4Screen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah4,
//                        onBackClick = { navController.navigate(Routes.Langkah3) },
//                        onNext = { navController.navigate(Routes.Langkah5BuatPin) }
//                    )
//                }
//                composable<Routes.Langkah5BuatPin> {
//                    Langkah5BuatPinScreen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah5BuatPin,
//                        onBackClick = { navController.navigate(Routes.Langkah4) },
//                        onNext = { navController.navigate(Routes.Langkah5KonfirmasiPin) }
//                    )
//                }
//                composable<Routes.Langkah5KonfirmasiPin> {
//                    Langkah5KonfirmasiPinScreen(
//                        navController = navController,
//                        currentRoute = Routes.Langkah5KonfirmasiPin,
//                        onBackClick = { navController.navigate(Routes.Langkah5BuatPin) },
//                        onNext = { navController.navigate(Routes.PengajuanDataBerhasil) }
//                    )
//                }
//                composable<Routes.PengajuanDataBerhasil> {
//                    LangkahBerhasilScreen(
//                    navController = navController,
//                    currentRoute = Routes.PengajuanDataBerhasil,
//                    onMulaiClick = { navController.navigate(Routes.AkunSaya) }
//                )
//                }
//                composable<Routes.AkunSaya> {
//                    DataPribadiScreen(
//                        navController = navController,
//                        currentRoute = Routes.AkunSaya,
//                        onBackClick = { navController.navigate(Routes.Profile) },
//                        onAjukanClick = { navController.navigate(Routes.Profile) }
//                    )
//                }
//                composable<Routes.AkunBank> {
//                    AkunBankScreen(
//                        navController = navController,
//                        currentRoute = Routes.AkunBank,
//                        onBackClick = { navController.navigate(Routes.Profile) },
//                        onTambahAkunClick = { navController.navigate(Routes.AkunBank) }
//                    )
//                }
//                composable<Routes.TambahAkunBank> {
//                    TambahBankScreen(
//                        navController = navController,
//                        currentRoute = Routes.AkunBank,
//                        onBackClick = { navController.navigate(Routes.Profile) },
//                        onTambahBank = { navController.navigate(Routes.TambahAkunBank) }
//                    )
//                }
//
//                composable<Routes.KetentuanLayanan> {
//                    SyaratKetentuanScreen(
//                        navController = navController,
//                        currentRoute = Routes.KetentuanLayanan,
//                        onBack = { navController.navigate(Routes.Profile) }
//                    )
//                }
//
//                composable<Routes.PertanyaanUmum> {
//                    PertanyaanUmumScreen(
//                        navController = navController,
//                        currentRoute = Routes.KetentuanLayanan,
//                        onBackClick = { navController.navigate(Routes.Profile) }
//                    )
//                }






            }
        }
    }
}
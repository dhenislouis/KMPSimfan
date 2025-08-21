//package org.kmp.simfan.components
//
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.animateLottieCompositionAsState
//import com.airbnb.lottie.compose.rememberLottieComposition
//import simfan.composeapp.generated.resources.Res
//
//@Composable
//actual fun SuccessAnimation(modifier: Modifier) {
//    val composition by rememberLottieComposition(
//        LottieCompositionSpec.JsonString(Res.raw.check_success)
//    )
//    val progress by animateLottieCompositionAsState(
//        composition = composition,
//        iterations = 1 // main sekali
//    )
//    LottieAnimation(
//        composition = composition,
//        progress = { progress },
//        modifier = modifier
//    )
//}

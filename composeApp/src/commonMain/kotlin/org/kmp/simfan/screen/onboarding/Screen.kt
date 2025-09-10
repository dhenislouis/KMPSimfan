package org.kmp.simfan.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.components.button.ComFilledButton
import org.kmp.simfan.core.Primary
import org.kmp.simfan.screen.onboarding.component.ContentOnboarding
import simfan.composeapp.generated.resources.Res

data class OnboardingPage(
    val icon: Painter,
    val label: String,
    val title: String,
    val description: String
)
@Composable
fun OnboardingScreen(
    onFinished: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0) { 4 } // jumlah screen
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        // Pager utama
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> OnboardingStep(
                    image = "files/onboard1.svg",
                    label = "Introducing Simpanan Deposito App",
                    title = "100% Online dengan Proses Praktis dan Cepat",
                    description = "Buka deposito langsung dari aplikasi tanpa harus ke kantor cabang. Simpan dana dengan lebih mudah, cepat, dan efisien."
                )
                1 -> OnboardingStep(
                    image = "files/onboard2.svg",
                    label = "Special Rate Offer",
                    title = "6.25% Bunga Kompetitif Keuntungan Maksimal",
                    description = "Dapatkan bunga tinggi untuk hasil maksimal dari dana yang kamu simpan. Pilihan ideal untuk pertumbuhan keuanganmu."
                )
                2 -> OnboardingStep(
                    image = "files/onboard3.svg",
                    label = "Protected by LPS & Trusted Banks",
                    title = "100% Aman oleh LPS & Bank Terpercaya",
                    description = "Dana dijamin LPS dan dikelola oleh bank mitra terpercaya untuk perlindungan dan ketenangan maksimal."
                )
                3 -> OnboardingStep(
                    image = "files/onboard4.svg",
                    label = "Maksimalkan Pengalaman Pengguna",
                    title = "Jelajahi Fitur Aplikasi & Dapatkan Bantuan",
                    description = "Nikmati layanan terbaik lewat aplikasi kami. Pilih paket sesuai kebutuhan, akses fitur unggulan."
                )
            }
        }

        // Overlay untuk klik kiri / kanan
//        Row(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Area klik kiri
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .background(Color.Transparent) // transparan
//                    .clickable(
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                    ) {
//                        coroutineScope.launch {
//                            val prev = (pagerState.currentPage - 1).coerceAtLeast(0)
//                            pagerState.animateScrollToPage(prev)
//                        }
//                    }
//            )
//
//            // Area klik kanan
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .background(Color.Transparent)
//                    .clickable(
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                        )  {
//                        coroutineScope.launch {
//                            val next = (pagerState.currentPage + 1)
//                                .coerceAtMost(pagerState.pageCount - 1)
//
//                            pagerState.animateScrollToPage(next)
//                        }
//                    }
//            )
//        }
//
//        // Skip + indikator
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onFinished() }) {
                Text("Skip", color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            IndicatorDots(
                totalDots = 4,
                selectedIndex = pagerState.currentPage
            )
        }

        // Tombol selesai
        if (pagerState.currentPage == pagerState.pageCount - 1) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                ComFilledButton(
                    onClick = { onFinished() },
                    modifier = Modifier.fillMaxWidth(),
                    text = "Selesai"
                )
            }
        }else{
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                ComFilledButton(
                    onClick = {
                        coroutineScope.launch {
                            val next = (pagerState.currentPage + 1)
                                .coerceAtMost(pagerState.pageCount - 1)
                            pagerState.animateScrollToPage(next)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = "Lanjut"
                )
            }
        }

    }
}


@Composable
fun OnboardingStep(
    image: String,
    label: String,
    title: String,
    description: String,
) {
    Box(modifier = Modifier.fillMaxSize().background(Primary)) {
        AsyncImage(
            model = Res.getUri(image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding( start = 24.dp,
                    end = 24.dp,
                    bottom = 64.dp)
        ) {
            ContentOnboarding(
                label = label,
                title = title,
                description = description,
                buttonText = "" // kosong karena tombol dihandle di OnboardingScreen
            )
        }
    }
}

@Composable
fun IndicatorDots(totalDots: Int, selectedIndex: Int) {
    Row {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(if (index == selectedIndex) 10.dp else 8.dp)
                    .background(
                        if (index == selectedIndex) Color.White else Color.LightGray,
                        shape = MaterialTheme.shapes.small
                    )
            )
        }
    }
}

@Preview
@Composable
fun OnboardBaruPrev(){
    OnboardingScreen(){}
}
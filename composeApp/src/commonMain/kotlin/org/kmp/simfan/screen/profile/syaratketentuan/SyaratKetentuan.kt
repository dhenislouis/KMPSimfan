package org.kmp.simfan.screen.profile.syaratketentuan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalDensity
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import simfan.composeapp.generated.resources.*

/**
 * 🚀 Voyager Screen Syarat & Ketentuan
 */
object SyaratKetentuanScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        SyaratKetentuanUI(
            onBack = { navigator.pop() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyaratKetentuanUI(
    onBack: () -> Unit = {}
) {
    val syaratList = listOf(
        "1. Definisi" to "Deposito adalah produk simpanan berjangka dengan suku bunga tetap...",
        "2. Penempatan Dana" to "Minimum penempatan dana untuk membuka deposito adalah sebesar Rp1.000.000...",
        "3. Jangka Waktu" to "Pilihan tenor deposito tersedia mulai dari 1 bulan, 3 bulan, 6 bulan...",
        "4. Suku Bunga dan Perhitungan" to "Suku bunga bersifat tetap selama jangka waktu yang telah disepakati...",
        "5. Pencairan Dana" to "Setelah jatuh tempo, dana pokok dan bunga bersih akan ditransfer otomatis...",
        "6. Risiko dan Jaminan" to "yang bekerja sama dijamin oleh LPS hingga batas maksimal...",
        "7. Perubahan Ketentuan" to "Pihak penyedia layanan berhak untuk mengubah syarat dan ketentuan ini...",
        "8. Lain-lain" to "Dengan menyetujui syarat dan ketentuan ini, pengguna menyatakan..."
    )

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Syarat & Ketentuan",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                actions = {
                    Spacer(Modifier.size(48.dp)) // balance kanan
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(syaratList) { (judul, isi) ->
                    Column {
                        Text(
                            text = judul,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF252C32),
                            modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                        )
                        Text(
                            text = isi,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 18.sp,
                            color = Color(0xFF22242F)
                        )
                    }
                }
            }

            // 🔹 Custom scrollbar auto-hide
            CustomScrollbarAutoHide(
                listState = listState,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 2.dp)
            )
        }
    }
}

@Composable
fun CustomScrollbarAutoHide(
    listState: androidx.compose.foundation.lazy.LazyListState,
    modifier: Modifier = Modifier,
    visibleHeightFraction: Float = 0.25f
) {
    val totalItems = listState.layoutInfo.totalItemsCount
    val visibleItems = listState.layoutInfo.visibleItemsInfo.size

    if (totalItems == 0 || visibleItems == 0) return

    val scrollFraction by remember {
        derivedStateOf {
            val firstVisible = listState.firstVisibleItemIndex
            val maxIndex = (totalItems - visibleItems).coerceAtLeast(1)
            firstVisible.toFloat() / maxIndex.toFloat()
        }
    }

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            isVisible = true
        } else {
            delay(1000)
            isVisible = false
        }
    }

    val density = LocalDensity.current

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BoxWithConstraints(
            modifier = modifier.fillMaxHeight(),
            contentAlignment = Alignment.TopEnd
        ) {
            val containerHeightPx = with(density) { maxHeight.toPx() }

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight(visibleHeightFraction)
                    .offset {
                        IntOffset(
                            x = 0,
                            y = (scrollFraction * (containerHeightPx * (1 - visibleHeightFraction))).toInt()
                        )
                    }
                    .background(
                        Color.Gray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewSyaratKetentuanUI() {
    SyaratKetentuanUI()
}

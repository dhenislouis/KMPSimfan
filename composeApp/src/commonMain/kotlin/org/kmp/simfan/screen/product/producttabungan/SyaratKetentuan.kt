package org.kmp.simfan.screen.product.producttabungan

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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyaratKetentuanProdukTabunganScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBack: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    val syaratList = listOf(
        "1. Definisi" to "Deposito adalah produk simpanan berjangka dengan suku bunga tetap, di mana dana nasabah akan disimpan dalam jangka waktu tertentu dan tidak dapat ditarik sebelum jatuh tempo, kecuali atas persetujuan khusus.",
        "2. Penempatan Dana" to "Minimum penempatan dana untuk membuka deposito adalah sebesar Rp1.000.000,- atau sesuai ketentuan yang berlaku. Penempatan dana dilakukan melalui metode pembayaran yang tersedia, seperti transfer bank, viertual account, atau metode lain yang disediakan oleh platform.",
        "3. Jangka Waktu" to "Pilihan tenor deposito tersedia mulai dari 1 bulan, 3 bulan, 6 bulan, hingga 12 bulan, tergantung produk dan mitra BPR yang dipilih oleh pengguna. Dana tidak dapat dicairkan sebelum jatuh tempo (breakable), kecuali pada produk tertentu yang menyatakan sebaliknya.",
        "4. Suku Bunga dan Perhitungan" to "Suku bunga bersifat tetap selama jangka waktu yang telah disepakati dan ditentukan pada saat penempatan deposito.\n\nPerhitungan bunga menggunakan metode gross (belum dipotong pajak), dan akan dikenakan pajak sesuai ketentuan perpajakan yang berlaku di indonesia.",
        "5. Pencairan Dana" to "Setelah jatuh tempo, dana pokok dan bunga bersih (setelah dipotong pajak) akan ditransfer otomatis ke rekening bank yang telah didaftarkan oleh nasabah pada saat registrasi.\nUntuk pencairan sebelum jatuh tempo, bunga tidak akan diberikan, dan nasabah harus mengajukan permintaan resmi ke penyedia layanan.",
        "6. Risiko dan Jaminan" to "Produk deposito dari mitra BPR yang bekerja sama dijamin oleh Lembaga Penjamin Simpanan (LPS) hingga batas maksimal sesuai regulasi, yakni Rp2.000.000.000 per nasabah per bank. Pengguna disarankan membaca detail produk masing-masing sebelum melakukan penempatan.",
        "7. Perubahan Ketentuan" to "Pihak penyedia layanan berhak untuk mengubah syarat dan ketentuan ini sewaktu-waktu, dengan pemberitahuan sebelumnya kepada pengguna melalui media resmi seperti email atau aplikasi.",
        "8. Lain-lain" to "Dengan menyetujui syarat dan ketentuan ini, pengguna menyatakan bahwa seluruh informasi yang diberikan adalah benar dan dapat dipertanggungjawabkan. pengguna juga menyatakan setuju untuk tunduk pada hukum dan peraturan yang berlaku di wilayah Republik Indonesia."
    )

    val listState = rememberLazyListState()
    var isChecked by remember { mutableStateOf(false) }

    // 🔹 Animasi perubahan warna tombol
    val buttonColor by animateColorAsState(
        targetValue = if (isChecked) Color(0xFF007AFF) else Color.Gray,
        label = "buttonColorAnim"
    )

    // 🔹 Animasi scale untuk checkbox
    val checkBoxScale by animateFloatAsState(
        targetValue = if (isChecked) 1.2f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "checkboxScaleAnim"
    )

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
                actions = { Spacer(Modifier.size(48.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.scale(checkBoxScale)
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Button1,
                                uncheckedColor = Color(0xFF252C32)
                            )
                        )
                    }
                    Text(
                        text = "Saya menyetujui Syarat & Ketentuan penempatan tabungan.",
                        fontSize = 13.sp,
                        color = Color(0xFF252C32)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { if (isChecked) onContinue() },
                    enabled = isChecked,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "TandaTangan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
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

            // 🔹 Scrollbar auto-hide
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
    listState: LazyListState,
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

//@Preview
//@Composable
//fun PreviewSyaratKetentuanUI() {
//    SyaratKetentuanProdukDepositoScreen()
//}

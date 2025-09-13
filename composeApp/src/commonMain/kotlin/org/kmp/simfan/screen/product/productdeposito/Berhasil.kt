package org.kmp.simfan.screen.product.productdeposito

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PenempatanDepositoBerhasilScreen(
    navController: NavController,
    currentRoute: Routes?,
    onBack: () -> Unit = {},
    onLihatSimpanan: () -> Unit = {},
    onKembaliBeranda: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Penempatan Deposito",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onLihatSimpanan,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
                ) {
                    Text("Lihat Simpananku", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = onKembaliBeranda,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text("Kembali ke Beranda", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 80.dp) // ✨ bikin snackbar muncul di atas bottomBar
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFDFF6E5)),
                contentAlignment = Alignment.Center
            ) {
                AnimatedSuccessCircleCheck(size = 40.dp) // ✅ Ganti icon dengan animasi centang
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Pengajuan Berhasil\nLakukan Pembayaran",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Sabtu, 20 Juni 2025 • 15.00 PM",
                fontSize = 13.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            val detailList = listOf(
                "No. Referensi" to "20250620123456789",
                "Jenis Transaksi" to "Penempatan Deposito",
                "Produk" to "Deposito 3 Bulan",
                "Nominal Setoran Awal" to "Rp 1.000.000",
                "Metode Pembayaran" to "BCA VA",
                "No. VA" to "123456789876545678",
                "Biaya Admin" to "Rp 3.500",
                "Total Dibayar" to "Rp 1.000.000"
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    detailList.forEachIndexed { index, (label, value) ->
                        DetailItem(
                            label = label,
                            value = value,
                            highlight = label == "Total Dibayar",
                            onCopy = { copiedText ->
                                scope.launch {
                                    snackbarHostState.showSnackbar("No. VA $copiedText berhasil disalin")
                                }
                            }
                        )
                        if (index < detailList.lastIndex) {
                            DashedDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedSuccessCircleCheck(size: Dp = 80.dp) {
    val progress by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 800, easing = LinearEasing),
        label = "circleAnim"
    )

    val checkProgress by animateFloatAsState(
        targetValue = if (progress >= 1f) 1f else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = 200),
        label = "checkAnim"
    )

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // 🔵 Lingkaran progres
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 6.dp.toPx()
            val sweep = 360 * progress

            drawArc(
                color = Color(0xFF34A853),
                startAngle = -90f,
                sweepAngle = sweep,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        // ✅ Check animation
        Canvas(modifier = Modifier.fillMaxSize(0.6f)) {
            val w = size.toPx() * 0.6f
            val h = size.toPx() * 0.6f

            val path = Path().apply {
                moveTo(w * 0.28f, h * 0.55f)

                if (checkProgress > 0f) {
                    // garis pertama (kiri bawah → tengah)
                    val midX = w * 0.28f + (w * 0.17f) * (checkProgress.coerceAtMost(0.5f) * 2)
                    val midY = h * 0.55f + (h * 0.15f) * (checkProgress.coerceAtMost(0.5f) * 2)
                    lineTo(midX, midY)
                }

                if (checkProgress > 0.5f) {
                    // garis kedua (tengah → kanan atas)
                    val secondProgress = (checkProgress - 0.5f) * 2
                    val endX = (w * 0.45f) + (w * 0.30f) * secondProgress
                    val endY = (h * 0.70f) - (h * 0.35f) * secondProgress
                    lineTo(endX, endY)
                }
            }

            drawPath(
                path = path,
                color = Color(0xFF34A853),
                style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}



@Composable
fun DetailItem(
    label: String,
    value: String,
    highlight: Boolean = false,
    onCopy: (String) -> Unit = {}
) {
    val clipboardManager = LocalClipboardManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 14.sp, color = Color.Black)

        if (label == "No. VA") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = value,
                    fontSize = 14.sp,
                    fontWeight = if (highlight) FontWeight.Bold else FontWeight.Normal,
                    color = if (highlight) Color.Black else Color.Gray,
                    textAlign = TextAlign.End
                )
                Spacer(Modifier.width(6.dp))
                IconButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(value))
                        onCopy(value)
                    },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.copy_icon), // ganti sesuai icon copy kamu
                        contentDescription = "Copy VA",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        } else {
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = if (highlight) FontWeight.Bold else FontWeight.Normal,
                color = if (highlight) Color.Black else Color.Gray,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun DashedDivider(
    color: Color = Color(0xFFE5E7EB),
    strokeWidth: Dp = 1.dp,
    dashWidth: Dp = 6.dp,
    gapWidth: Dp = 4.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(strokeWidth)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashWidth.toPx(), gapWidth.toPx()), 0f
            )
        )
    }
}

//@Preview
//@Composable
//fun PreviewPenempatanDepositoBerhasil() {
//    PenempatanDepositoBerhasilScreen()
//}

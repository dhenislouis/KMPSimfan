package org.kmp.simfan.screen.profile.tandatangan

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import simfan.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun TandaTanganScreen(
    navController: NavController, currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onSave: (ImageBitmap) -> Unit = {}
) {
//    val path = remember { Path() }
    val strokes = remember { mutableStateListOf<MutableList<Offset>>() } // list coretan, tiap coretan = list titik
    var currentStroke = remember { mutableStateListOf<Offset>() }
    val scope = rememberCoroutineScope()
    val signatureState = remember { SignaturePadState() }
    var canvasWidth by remember { mutableStateOf(0f) }
    var canvasHeight by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "Tanda Tangan",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Area Canvas
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f) // ⬅️ cuma ambil 50% layar
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // isi penuh bagian atas (dalam 50%)
                    .background(Color.White)
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))

            ) {
                SignaturePad(
                    modifier = Modifier.fillMaxSize(),
                    state = signatureState,
                    onSizeChanged = { w, h ->
                        canvasWidth = w
                        canvasHeight = h
                    }
                )
            }

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = { signatureState.clear() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text("Ulangi")
                }

                Button(
                    onClick = {
                        scope.launch {
                            // contoh: hasilkan dummy image
                            val imageBitmap = ImageBitmap(800, 400)
                            onSave(imageBitmap)
                        }
//                        scope.launch {
//                            // bikin bitmap dengan ukuran canvas
//                            val bitmap = Bitmap.createBitmap(
//                                canvasWidth.toInt(),
//                                canvasHeight.toInt(),
//                                Bitmap.Config.ARGB_8888
//                            )
//
//                            val canvas = androidx.compose.ui.graphics.Canvas(
//                                androidx.compose.ui.graphics.AndroidCanvas(bitmap)
//                            )
//
//                            // gambar ulang stroke ke bitmap
//                            strokes.forEach { stroke ->
//                                for (i in 0 until stroke.size - 1) {
//                                    canvas.drawLine(
//                                        stroke[i],
//                                        stroke[i + 1],
//                                        androidx.compose.ui.graphics.Paint().apply {
//                                            color = Color.Black
//                                            strokeWidth = 4f
//                                        }
//                                    )
//                                }
//                            }
//                            onSave(bitmap)
//                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Button1)
                ) {
                    Text("Simpan", color = Color.White)
                }
            }
        }
    }
}


private fun Offset.clamp(maxWidth: Float, maxHeight: Float): Offset {
    val x = x.coerceIn(0f, maxWidth)
    val y = y.coerceIn(0f, maxHeight)
    return Offset(x, y)
}

@Composable
fun SignaturePad(
    modifier: Modifier = Modifier,
    state: SignaturePadState,
    onSizeChanged: (Float, Float) -> Unit = { _, _ -> }
) {
    var canvasWidth by remember { mutableStateOf(0f) }
    var canvasHeight by remember { mutableStateOf(0f) }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val clamped = offset.clamp(canvasWidth, canvasHeight)
                        state.startStroke(clamped)
                    },
                    onDrag = { change, _ ->
                        state.addPoint(change.position.clamp(canvasWidth, canvasHeight))
                    },
                    onDragEnd = {
                        state.endStroke()
                    }
                )
            },
        onDraw = {
            canvasWidth = size.width
            canvasHeight = size.height
            onSizeChanged(canvasWidth, canvasHeight)

            state.strokes.forEach { stroke ->
                for (i in 0 until stroke.size - 1) {
                    drawLine(
                        color = Color.Black,
                        start = stroke[i],
                        end = stroke[i + 1],
                        strokeWidth = 4f
                    )
                }
            }
        }
    )
}

//@Preview
//@Composable
//fun PreviewTandaTanganScreen() {
//    TandaTanganScreen (
//        onBackClick = {},
//        onSave = { /* dummy: jangan convert bitmap di preview */ }
//    )
//}
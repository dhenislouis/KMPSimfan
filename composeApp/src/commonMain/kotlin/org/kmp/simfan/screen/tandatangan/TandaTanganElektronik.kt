// commonMain/kotlin/org/kmp/simfan/screen/tandatangan/SignatureScreen.kt
package org.kmp.simfan.screen.tandatangan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas as ComposeCanvas

// Satu coret = satu list titik
typealias StrokePoints = MutableList<Offset>

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignatureScreen(
    onBack: () -> Unit = {},
    onSignatureSaved: (String?) -> Unit = {}
) {
    // Kumpulan coretan
    var strokes by remember { mutableStateOf(listOf<StrokePoints>()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tanda Tangan", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        // Ganti dengan ikon panah jika punya aset
                        Text("←", fontSize = 20.sp)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Area tanda tangan
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                ComposeCanvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { pos ->
                                    // mulai coretan baru
                                    strokes = (strokes + mutableListOf(pos)) as List<StrokePoints>
                                },
                                onDrag = { change, _ ->
                                    // tambah titik ke coretan terakhir
                                    if (strokes.isNotEmpty()) {
                                        strokes.last().add(change.position)
                                    }
                                }
                            )
                        }
                ) {
                    // gambar semua coretan
                    strokes.forEach { points ->
                        if (points.size > 1) {
                            val p = Path().apply {
                                moveTo(points.first().x, points.first().y)
                                for (i in 1 until points.size) {
                                    lineTo(points[i].x, points[i].y)
                                }
                            }
                            drawPath(p, Color.Black, style = Stroke(width = 4f))
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Tombol Hapus (reset tanda tangan)
            OutlinedButton(
                onClick = { strokes = emptyList() },
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(50)
            ) {
                Text("Hapus", fontSize = 14.sp, color = Color.Black)
            }

            Spacer(Modifier.height(32.dp))

            // Tombol Kirim
            Button(
                onClick = {
                    val filePath = saveSignatureToFile(
                        strokes = strokes.map { it.toList() }, // immutable copy
                        width = 800,
                        height = 400
                    )
                    onSignatureSaved(filePath)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E86FF))
            ) {
                Text("Kirim", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

expect fun saveSignatureToFile(
    strokes: List<List<Offset>>,
    width: Int,
    height: Int
): String?

package org.kmp.simfan.screen.tandatangan

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.os.Environment
import androidx.compose.ui.geometry.Offset
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

actual fun saveSignatureToFile(
    strokes: List<List<Offset>>,
    width: Int,
    height: Int
): String? {
    // kosong? -> batal
    if (strokes.isEmpty() || strokes.all { it.size < 2 }) return null

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.drawColor(android.graphics.Color.WHITE)

    val paint = Paint().apply {
        color = android.graphics.Color.BLACK
        strokeWidth = 6f
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    // gambar tiap coretan
    strokes.forEach { pts ->
        if (pts.size > 1) {
            val p = Path()
            p.moveTo(pts.first().x, pts.first().y)
            for (i in 1 until pts.size) p.lineTo(pts[i].x, pts[i].y)
            canvas.drawPath(p, paint)
        }
    }

    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "TTD_$timeStamp.png"

    // Simpan ke folder dokumen aplikasi (tidak butuh permission, aman Android 10+)
    val dir = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
        "SimfanSignatures"
    )
    if (!dir.exists()) dir.mkdirs()
    val outFile = File(dir, fileName)

    return try {
        FileOutputStream(outFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        outFile.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

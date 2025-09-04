package org.kmp.simfan.screen.tandatangan

import androidx.compose.ui.geometry.Offset
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.*
import platform.Foundation.*
import platform.UIKit.*

@OptIn(ExperimentalForeignApi::class)
actual fun saveSignatureToFile(
    strokes: List<List<Offset>>,
    width: Int,
    height: Int
): String? {
    if (strokes.isEmpty() || strokes.all { it.size < 2 }) return null

    val w = width.toDouble()
    val h = height.toDouble()

    UIGraphicsBeginImageContextWithOptions(CGSizeMake(w, h), true, 0.0)
//    val ctx = UIGraphicsGetCurrentContext() ?: return null.also { UIGraphicsEndImageContext() }
//
//    CGContextSetFillColorWithColor(ctx, UIColor.whiteColor.CGColor)
//    CGContextFillRect(ctx, CGRectMake(0.0, 0.0, w, h))
//
//    CGContextSetStrokeColorWithColor(ctx, UIColor.blackColor.CGColor)
//    CGContextSetLineWidth(ctx, 4.0)
//    CGContextSetLineCap(ctx, CGLineCap.kCGLineCapRound)
//    CGContextSetLineJoin(ctx, CGLineJoin.kCGLineJoinRound)

//    strokes.forEach { pts ->
//        if (pts.size > 1) {
//            CGContextBeginPath(ctx)
//            CGContextMoveToPoint(ctx, pts.first().x.toDouble(), pts.first().y.toDouble())
//            for (i in 1 until pts.size) {
//                val p = pts[i]
//                CGContextAddLineToPoint(ctx, p.x.toDouble(), p.y.toDouble())
//            }
//            CGContextStrokePath(ctx)
//        }
//    }

    val image = UIGraphicsGetImageFromCurrentImageContext()
    UIGraphicsEndImageContext()
    if (image == null) return null

    val data = UIImagePNGRepresentation(image) ?: return null

    val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
    val docs = paths.firstOrNull() as? String ?: return null
    val filePath = "$docs/TTD_${NSDate().timeIntervalSince1970}.png"

    return if (data.writeToFile(filePath, true)) filePath else null
}

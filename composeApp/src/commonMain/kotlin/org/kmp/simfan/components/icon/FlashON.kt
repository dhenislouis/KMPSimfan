package org.kmp.simfan.components.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FlashON: ImageVector
    get() {
        if (_FlashON != null) {
            return _FlashON!!
        }
        _FlashON = ImageVector.Builder(
            name = "FlashON",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(24f)
                    horizontalLineToRelative(-24f)
                    close()
                }
            ) {
                path(
                    fill = SolidColor(Color.White),
                    fillAlpha = 0.7f,
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(13.232f, 1.36f)
                    curveTo(13.864f, 0.602f, 15.095f, 1.12f, 14.995f, 2.102f)
                    lineTo(14.289f, 9f)
                    horizontalLineTo(20f)
                    curveTo(20.19f, 9f, 20.376f, 9.054f, 20.536f, 9.156f)
                    curveTo(20.697f, 9.258f, 20.825f, 9.404f, 20.905f, 9.576f)
                    curveTo(20.986f, 9.748f, 21.016f, 9.939f, 20.991f, 10.128f)
                    curveTo(20.967f, 10.316f, 20.89f, 10.494f, 20.768f, 10.64f)
                    lineTo(10.768f, 22.64f)
                    curveTo(10.136f, 23.398f, 8.905f, 22.88f, 9.005f, 21.898f)
                    lineTo(9.711f, 15f)
                    horizontalLineTo(4f)
                    curveTo(3.81f, 15f, 3.624f, 14.946f, 3.464f, 14.844f)
                    curveTo(3.303f, 14.742f, 3.175f, 14.596f, 3.095f, 14.424f)
                    curveTo(3.014f, 14.252f, 2.984f, 14.061f, 3.009f, 13.872f)
                    curveTo(3.033f, 13.684f, 3.11f, 13.506f, 3.232f, 13.36f)
                    lineTo(13.232f, 1.36f)
                    close()
                }
            }
        }.build()

        return _FlashON!!
    }

@Suppress("ObjectPropertyName")
private var _FlashON: ImageVector? = null

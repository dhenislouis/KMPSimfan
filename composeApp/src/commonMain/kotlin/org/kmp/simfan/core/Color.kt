package org.kmp.simfan.core

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

var Primary = Color(0xFF3366FF)
var Secondary = Color(0xFFFAB46B)
var BackgroundPrimary = Color(0xFFF7F7F8)
var BackgroundSecondary = Color(0xFF84919A)
var Button1 = Color(0xFF023FFC)

internal val LightColorScheme = lightColorScheme(
    primary = Primary,
    background = Color.White,
//    onBackground = LightTextColor,
    surface = Primary,
//    onSurface = DarkGrayPrimary,
    secondary = Secondary,
//    onSecondary = LightTextColor
)

//internal val DarkColorScheme = darkColorScheme(
//    primary = Primary,
//    background = DarkBackground,
//    onBackground = DarkTextColor,
//    surface = DarkSurface,
//    onSurface = WhitePrimary,
//    secondary = DarkGrayButton,
//    onSecondary = DarkTextColor
//)
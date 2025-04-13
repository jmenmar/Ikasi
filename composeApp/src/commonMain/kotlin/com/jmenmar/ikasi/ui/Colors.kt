package com.jmenmar.ikasi.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val White = Color(0xFFF5F7F9)

val GreyLight = Color(0xFFE3E6E8)
val GreyMedium = Color(0xFFA9AAAC)
val GreyDark = Color(0xFF424242)
val GreyDarkest = Color(0xFF171717)

val BlueDarkest = Color(0xFF212231)
val BlueDark = Color(0xFF292C3D)
val BlueMedium = Color(0xFF5194F7)
val BlueLight = Color(0xFFCFDFFF)

val Blue = Color(0xFF5EB0E3)
val Green = Color(0xFF64C29C)
val Yellow = Color(0xFFF3B455)
val Orange = Color(0xFFEE804A)
val Pink = Color(0xFFDF5284)
val Red = Color(0xFFD57076)

internal val LightColorScheme = lightColorScheme(
    primary = BlueMedium,
    onPrimary = White,
    background = GreyLight,
    surface = White,
    onSurface = GreyDarkest,
    secondary = GreyMedium,
    tertiary = GreyDark,
    onTertiary = GreyDarkest,
    surfaceContainer = White,
)

internal val DarkColorScheme = darkColorScheme(
    primary = BlueMedium,
    onPrimary = White,
    background = BlueDarkest,
    surface = BlueDark,
    onSurface = White,
    secondary = GreyMedium,
    tertiary = BlueLight,
    onTertiary = GreyDarkest,
    surfaceContainer = BlueDark,
)
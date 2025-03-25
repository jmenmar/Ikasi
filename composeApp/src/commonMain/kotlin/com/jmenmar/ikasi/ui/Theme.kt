package com.jmenmar.ikasi.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun IkasiTheme(
    isDarkTheme: Boolean?,
    content: @Composable () -> Unit
) {
    val colorScheme = when (isDarkTheme) {
        true -> DarkColorScheme
        false -> LightColorScheme
        null -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
//        typography = AppTypography(),
//        shapes = Shapes,
        content = content,
    )
}
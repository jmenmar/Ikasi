package com.jmenmar.ikasi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun IkasiTheme(
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
//        typography = AppTypography(),
//        shapes = Shapes,
        content = content,
    )
}
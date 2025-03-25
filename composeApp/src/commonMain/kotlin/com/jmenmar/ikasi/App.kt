package com.jmenmar.ikasi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jmenmar.ikasi.presentation.navigation.Navigation
import com.jmenmar.ikasi.ui.IkasiTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var isDarkTheme by remember { mutableStateOf<Boolean?>(null) }

    IkasiTheme(isDarkTheme = isDarkTheme) {
        Navigation(
            onThemeChange = { isDarkTheme = it }
        )
    }
}
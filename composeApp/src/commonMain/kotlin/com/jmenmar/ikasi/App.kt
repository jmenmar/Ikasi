package com.jmenmar.ikasi

import androidx.compose.runtime.Composable
import com.jmenmar.ikasi.presentation.navigation.Navigation
import com.jmenmar.ikasi.ui.IkasiTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    IkasiTheme {
        Navigation()
    }
}
package com.jmenmar.ikasi.presentation.screens.settings

import com.jmenmar.ikasi.domain.model.Settings

data class SettingsState(
    val settings: Settings? = null,
    val totalTime: Int = 0,
    val isConfirmDialogVisible: Boolean = false,
    val isDeletingVocabulary: Boolean = false,
    val isNavigating: Boolean = false,
)

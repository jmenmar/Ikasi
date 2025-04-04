package com.jmenmar.ikasi.presentation.screens.main

import com.jmenmar.ikasi.domain.model.Badge

data class MainState(
    val showNotification: Boolean = false,
    val newBadgesCompleted: List<Badge> = emptyList(),
)

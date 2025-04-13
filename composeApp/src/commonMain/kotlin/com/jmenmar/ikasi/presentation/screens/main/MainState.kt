package com.jmenmar.ikasi.presentation.screens.main

import com.jmenmar.ikasi.domain.model.Badge

data class MainState(
    val newBadgesCompleted: List<Badge> = emptyList(),
    val notifications: List<Badge> = emptyList(),
)

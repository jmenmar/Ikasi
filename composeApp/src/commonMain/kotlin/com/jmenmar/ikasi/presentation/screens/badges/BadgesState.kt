package com.jmenmar.ikasi.presentation.screens.badges

import com.jmenmar.ikasi.domain.model.Badge

data class BadgesState(
    val isListView: Boolean = false,
    val badges: List<Badge> = emptyList(),
)

package com.jmenmar.ikasi.presentation.screens.badges

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.presentation.screens.badges.components.BadgesGridView
import com.jmenmar.ikasi.presentation.screens.badges.components.BadgesListView
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun BadgesScreen(
    innerPadding: PaddingValues,
    viewModel: BadgesViewModel = koinViewModel<BadgesViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BadgesView(
        innerPadding = innerPadding,
        badges = state.badges,
        isListStyle = state.isListView,
        onToggleStyle = { viewModel.toggleStyle() }
    )
}

@Composable
fun BadgesView(
    innerPadding: PaddingValues,
    badges: List<Badge> = emptyList(),
    isListStyle: Boolean,
    onToggleStyle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
    ) {
        if (isListStyle) {
            BadgesListView(
                badges = badges,
                isListStyle = isListStyle,
                onToggleStyle = onToggleStyle
            )
        } else {
            BadgesGridView(
                badges = badges,
                isListStyle = isListStyle,
                onToggleStyle = onToggleStyle
            )
        }
    }
}
package com.jmenmar.ikasi.presentation.screens.badges.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge

@Composable
fun BadgesGridView(
    badges: List<Badge>,
    isListStyle: Boolean,
    onToggleStyle: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            BadgesHeaderView(
                badges = badges,
                isListStyle = isListStyle,
                onToggleStyle = onToggleStyle
            )
        }
        items(badges) { badge ->
            BadgeGridCard(
                badge = badge
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
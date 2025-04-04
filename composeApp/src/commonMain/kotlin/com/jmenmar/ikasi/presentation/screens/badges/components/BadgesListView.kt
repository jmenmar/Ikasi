package com.jmenmar.ikasi.presentation.screens.badges.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge

@Composable
fun BadgesListView(
    badges: List<Badge>,
    isListStyle: Boolean,
    onToggleStyle: () -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            BadgesHeaderView(
                badges = badges,
                isListStyle = isListStyle,
                onToggleStyle = onToggleStyle,
            )
        }
        items(badges) { badge ->
            BadgeListCard(
                badge = badge
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
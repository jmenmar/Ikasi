package com.jmenmar.ikasi.presentation.screens.badges.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.presentation.components.BasicCard
import org.jetbrains.compose.resources.stringResource

@Composable
fun BadgeListCard(
    badge: Badge
) {
    BasicCard(
        cardModifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            BadgeIcon(badge = badge)
            Column(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = stringResource(badge.type.title),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (badge.completed) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = stringResource(badge.type.description),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (badge.completed) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            }
        }
    }
}
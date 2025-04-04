package com.jmenmar.ikasi.presentation.screens.badges.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.presentation.components.BasicCard
import org.jetbrains.compose.resources.stringResource

@Composable
fun BadgeGridCard(
    badge: Badge
) {
    BasicCard(
        cardModifier = Modifier.width(100.dp).heightIn(min = 140.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BadgeIcon(badge = badge)
            Text(
                text = stringResource(badge.type.title),
                textAlign = TextAlign.Center,
                maxLines = 2,
                style = MaterialTheme.typography.labelLarge,
                color = if (badge.completed) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
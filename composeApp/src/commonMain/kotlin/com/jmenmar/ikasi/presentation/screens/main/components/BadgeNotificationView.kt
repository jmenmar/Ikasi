package com.jmenmar.ikasi.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.presentation.components.BasicCard
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.badge_unlocked
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BadgeNotificationView(
    badge: Badge
) {
    BasicCard(
        cardModifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(
                            color =
                                if (badge.completed) badge.type.color
                                else badge.type.color.copy(alpha = 0.2f)
                        )
                ) {
                    Icon(
                        modifier = Modifier
                            .size(45.dp)
                            .padding(8.dp),
                        painter = painterResource(badge.type.icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            Column(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = stringResource(badge.type.title),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (badge.completed) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = stringResource(Res.string.badge_unlocked),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (badge.completed) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            }
        }

    }
}
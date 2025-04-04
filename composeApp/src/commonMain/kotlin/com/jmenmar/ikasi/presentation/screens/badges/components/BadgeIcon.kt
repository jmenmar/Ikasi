package com.jmenmar.ikasi.presentation.screens.badges.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge
import org.jetbrains.compose.resources.painterResource

@Composable
fun BadgeIcon(badge: Badge) {
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
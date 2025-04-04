package com.jmenmar.ikasi.presentation.screens.badges.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Badge
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.badges
import ikasi.composeapp.generated.resources.ic_grid_view
import ikasi.composeapp.generated.resources.ic_list_view
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BadgesHeaderView(
    badges: List<Badge>,
    isListStyle: Boolean,
    onToggleStyle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 8.dp),
            colors = IconButtonDefaults.iconButtonColors().copy(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = { onToggleStyle() }
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(
                    if (isListStyle) Res.drawable.ic_grid_view
                    else Res.drawable.ic_list_view
                ),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background,
            )
        }
        Text(
            text = "${badges.count { it.completed }} / ${badges.size}",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "🏆 ${stringResource(Res.string.badges)}")
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            modifier = Modifier.height(6.dp),
            progress = { badges.count { it.completed }.toFloat() / badges.size },
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surface,
            gapSize = 0.dp,
            drawStopIndicator = { },
        )
    }
}
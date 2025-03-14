package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.home
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeHeader(
    onNavigateToSettings: () -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(Res.string.home),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            colors = IconButtonDefaults.iconButtonColors().copy(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = { onNavigateToSettings() }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = ""
            )
        }
    }
}
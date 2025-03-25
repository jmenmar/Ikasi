package com.jmenmar.ikasi.presentation.screens.settings.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.ic_dark_mode
import ikasi.composeapp.generated.resources.ic_light_mode
import org.jetbrains.compose.resources.painterResource

@Composable
fun ThemeSwitch(
    isDarkThemeSelected: Boolean,
    onSwitchTheme: (Boolean) -> Unit = {}
) {
    Switch(
        checked = isDarkThemeSelected,
        onCheckedChange = {
            onSwitchTheme(it)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.surface,
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            checkedBorderColor = MaterialTheme.colorScheme.primary,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.surface,
            uncheckedBorderColor = MaterialTheme.colorScheme.surface,
        ),
        thumbContent = if (isDarkThemeSelected) {
            {
                Icon(
                    painter = painterResource(Res.drawable.ic_dark_mode),
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            {
                Icon(
                    painter = painterResource(Res.drawable.ic_light_mode),
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        }
    )
}
package com.jmenmar.ikasi.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.screens.diary.utils.formatMinutesToHours
import com.jmenmar.ikasi.presentation.utils.toStringFormat
import com.jmenmar.ikasi.ui.Pink
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.dark_mode
import ikasi.composeapp.generated.resources.donate_to_the_author
import ikasi.composeapp.generated.resources.ic_donate
import ikasi.composeapp.generated.resources.reset_progress
import ikasi.composeapp.generated.resources.start_date
import ikasi.composeapp.generated.resources.total_time
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    startDate: LocalDate,
    totalTime: Int,
    onThemeChange: (Boolean) -> Unit = {},
    onReset: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.dark_mode),
            )
            Spacer(modifier = Modifier.weight(1f))
            ThemeSwitch(
                isDarkThemeSelected = isDarkTheme,
                onSwitchTheme = onThemeChange
            )
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.start_date),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = startDate.toStringFormat(),
            )
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.total_time),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = formatMinutesToHours(totalTime),
            )
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                uriHandler.openUri("https://ko-fi.com/jmenmar")
            },
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.ic_donate),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = stringResource(Res.string.donate_to_the_author),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            shape = RoundedCornerShape(12.dp),
            onClick = onReset,
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = Pink
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
                Text(text = stringResource(Res.string.reset_progress))
            }
        }
    }
}
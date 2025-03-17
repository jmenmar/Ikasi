package com.jmenmar.ikasi.presentation.screens.diary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.components.BasicCard
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.ic_bolt
import ikasi.composeapp.generated.resources.streak
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StreakView(
    streak: Int
) {
    BasicCard(
        cardModifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.streak),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.width(18.dp))
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (1..7).forEach {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(Res.drawable.ic_bolt),
                        contentDescription = null,
                        tint = if (it <= streak) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background
                    )
                }
            }

        }
    }
}
package com.jmenmar.ikasi.presentation.screens.diary.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.presentation.utils.LevelProgress
import com.jmenmar.ikasi.presentation.utils.formatMinutesToHours
import com.jmenmar.ikasi.ui.BlueDarkest
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.experience_x
import ikasi.composeapp.generated.resources.ic_bolt
import ikasi.composeapp.generated.resources.level_x
import ikasi.composeapp.generated.resources.next_level_x
import ikasi.composeapp.generated.resources.streak
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DiaryExperienceView(
    totalXp: LevelProgress,
    streak: Int,
) {
    var progress by remember { mutableFloatStateOf(0f) }

    val progressAnimate by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = 250,
            easing = LinearOutSlowInEasing
        )
    )

    BasicCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box {
                CircularProgressIndicator(
                    progress = { 1f },
                    modifier = Modifier.size(size = 70.dp),
                    color = MaterialTheme.colorScheme.background,
                    strokeWidth = 8.dp,
                    trackColor = BlueDarkest,
                    strokeCap = StrokeCap.Round,
                )
                CircularProgressIndicator(
                    progress = { progressAnimate },
                    modifier = Modifier.size(size = 70.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 8.dp,
                    trackColor = Color.Transparent,
                    strokeCap = StrokeCap.Round,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1.9f),
            ) {
                Text(
                    text = stringResource(Res.string.level_x, totalXp.level),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = stringResource(
                        Res.string.experience_x,
                        formatMinutesToHours(totalXp.totalXp)
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                if (totalXp.level < 100) {
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = stringResource(
                            Res.string.next_level_x,
                            formatMinutesToHours(totalXp.totalXp - totalXp.currentLevelXp + totalXp.requiredXp)
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.1f)
                    .padding(start = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = streak.toString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(Res.drawable.ic_bolt),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = stringResource(Res.string.streak),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    LaunchedEffect(true) {
        progress = totalXp.currentLevelXp.toFloat() / totalXp.requiredXp.toFloat()
    }
}
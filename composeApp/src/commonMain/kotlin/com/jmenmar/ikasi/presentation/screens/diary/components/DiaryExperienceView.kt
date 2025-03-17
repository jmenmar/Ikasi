package com.jmenmar.ikasi.presentation.screens.diary.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.presentation.utils.LevelProgress
import com.jmenmar.ikasi.presentation.utils.formatMinutesToHours
import com.jmenmar.ikasi.ui.BlueDarkest

@Composable
fun DiaryExperienceView(
    totalXp: LevelProgress
) {
    var progress by remember { mutableFloatStateOf(0f) }

    // animation
    val progressAnimate by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1500,//animation duration
            delayMillis = 250,//delay before animation start
            easing = LinearOutSlowInEasing
        )
    )

    BasicCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box {
                CircularProgressIndicator(
                    progress = { 1f },
                    modifier = Modifier.size(size = 80.dp),
                    color = BlueDarkest,
                    strokeWidth = 8.dp,
                    trackColor = BlueDarkest,
                    strokeCap = StrokeCap.Round,
                )
                CircularProgressIndicator(
                    progress = { progressAnimate },
                    modifier = Modifier.size(size = 80.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 8.dp,
                    trackColor = Color.Transparent,
                    strokeCap = StrokeCap.Round,
                )
            }
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Level ${totalXp.level}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Experience: ${formatMinutesToHours(totalXp.totalXp)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                if (totalXp.level < 100) {
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = "Next Level: ${formatMinutesToHours(totalXp.totalXp - totalXp.currentLevelXp + totalXp.requiredXp)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    LaunchedEffect(true) {
        progress = totalXp.currentLevelXp.toFloat() / totalXp.requiredXp.toFloat()
    }
}
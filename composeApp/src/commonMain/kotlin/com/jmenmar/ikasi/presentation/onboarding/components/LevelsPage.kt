package com.jmenmar.ikasi.presentation.onboarding.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.ui.Blue
import com.jmenmar.ikasi.ui.BlueDarkest

@Composable
fun LevelsPage(
    hasSeen: Boolean = false,
    isLevelOne: Boolean = false,
    onUpToLevelOne: () -> Unit = {},
) {
    val progress by remember { mutableFloatStateOf(0f) }
    val progressAnimate by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = 250,
            easing = LinearOutSlowInEasing
        )
    )

    OnboardingPageView(
        title = "Need an extra motivation?",
        description = "Select the period of your challenge",
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { 1f },
                    modifier = Modifier.size(size = 80.dp),
                    color = MaterialTheme.colorScheme.surface,
                    strokeWidth = 8.dp,
                    trackColor = BlueDarkest,
                    strokeCap = StrokeCap.Round,
                )
                CircularProgressIndicator(
                    progress = { progressAnimate },
                    modifier = Modifier.size(size = 80.dp),
                    color = Blue,
                    strokeWidth = 8.dp,
                    trackColor = Color.Transparent,
                    strokeCap = StrokeCap.Round,
                )
                IconButton(
                    onClick = onUpToLevelOne,
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "",
                    )
                }
            }
            Text(
                text = if (isLevelOne) "Level 1" else "Level 0",
                style = MaterialTheme.typography.titleLarge
            )
        }
        LaunchedEffect(hasSeen) {
            if (hasSeen) {
                onUpToLevelOne()
            }
        }
    }
}
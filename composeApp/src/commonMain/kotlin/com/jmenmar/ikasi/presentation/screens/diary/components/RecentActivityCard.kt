package com.jmenmar.ikasi.presentation.screens.diary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.components.AnimatedLinearProgressIndicator
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.presentation.screens.diary.utils.ActivityPeriod
import com.jmenmar.ikasi.presentation.screens.diary.utils.formatMinutesToHours
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecentActivityCard(
    period: ActivityPeriod,
    maxValue: Int,
    activities: List<Activity>,
    onPeriodChange: (ActivityPeriod) -> Unit = {},
) {
    BasicCard {
        ActivityType.entries.sortedBy { it.priority }.forEach {
            val progress = if (maxValue <= 0) {
                0f
            } else {
                activities.filter { activity ->
                    activity.type == it }.sumOf { it.time }.toFloat() / maxValue.toFloat()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = it.color)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp),
                        painter = painterResource(it.icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Text(
                    modifier = Modifier.weight(3f),
                    text = stringResource(it.title)
                )
                Box(
                    modifier = Modifier
                        .weight(6f)
                        .height(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    AnimatedLinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp),
                        indicatorProgress = progress,
                        color = it.color,
                        trackColor = MaterialTheme.colorScheme.background,
                        gapSize = 0.dp,
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.CenterEnd)
                            .offset(y = (-1).dp)
                            .padding(end = 4.dp),
                        text = formatMinutesToHours(
                            activities
                                .filter { activity -> activity.type == it }
                                .sumOf { it.time }
                        ),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActivityPeriod.entries.forEach {
                PeriodChip(
                    modifier = Modifier.weight(1f),
                    activityPeriod = it,
                    selected = it == period,
                    onPeriodChange = onPeriodChange,
                )
            }
        }
    }
}

@Composable
fun PeriodChip(
    modifier: Modifier = Modifier,
    activityPeriod: ActivityPeriod,
    selected: Boolean = false,
    onPeriodChange: (ActivityPeriod) -> Unit = {},
) {
    FilterChip(
        modifier = modifier,
        onClick = { onPeriodChange(activityPeriod) },
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(activityPeriod.title),
                textAlign = TextAlign.Center
            )
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
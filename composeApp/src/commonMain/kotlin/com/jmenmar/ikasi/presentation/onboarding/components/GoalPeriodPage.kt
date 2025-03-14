package com.jmenmar.ikasi.presentation.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.onboarding.GoalPeriod
import org.jetbrains.compose.resources.stringResource

@Composable
fun GoalPeriodPage(
    selectedPeriod: GoalPeriod?,
    onSelectPeriod: (GoalPeriod) -> Unit = {},
) {
    OnboardingPageView(
        title = "Need an extra motivation?",
        description = "Select the period of your challenge",
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            GoalRangeChip(
                goalPeriod = GoalPeriod.YEAR,
                selected = selectedPeriod == GoalPeriod.YEAR,
                onSelect = onSelectPeriod,
            )
            GoalRangeChip(
                goalPeriod = GoalPeriod.HUNDRED,
                selected = selectedPeriod == GoalPeriod.HUNDRED,
                onSelect = onSelectPeriod,
            )
            GoalRangeChip(
                goalPeriod = GoalPeriod.CUSTOM,
                selected = selectedPeriod == GoalPeriod.CUSTOM,
                onSelect = onSelectPeriod,
            )
        }
    }
}

@Composable
fun GoalRangeChip(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    goalPeriod: GoalPeriod,
    onSelect: (GoalPeriod) -> Unit = {},
) {
    FilterChip(
        modifier = modifier.fillMaxWidth(),
        onClick = { onSelect(goalPeriod) },
        label = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(goalPeriod.title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "activityPeriod.label",
                    textAlign = TextAlign.Center
                )
            }
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
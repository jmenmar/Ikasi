package com.jmenmar.ikasi.presentation.onboarding

import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.goal_100_days
import ikasi.composeapp.generated.resources.goal_365_days
import org.jetbrains.compose.resources.StringResource

enum class GoalPeriod(
    val title: StringResource
) {
    YEAR (title = Res.string.goal_365_days),
    HUNDRED (title = Res.string.goal_100_days),
    CUSTOM (title = Res.string.goal_365_days),
}
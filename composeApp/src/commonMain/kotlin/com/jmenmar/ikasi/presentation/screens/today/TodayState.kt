package com.jmenmar.ikasi.presentation.screens.today

import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType

data class TodayState(
    val todayActivities: List<Activity> = emptyList(),
    val addActivitySheetVisible: Boolean = false,
    val selectedActivityType: ActivityType = ActivityType.SPEAKING,
)
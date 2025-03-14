package com.jmenmar.ikasi.presentation.screens.home

import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.model.Banner
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.utils.ActivityPeriod

data class HomeState(
    val totalDays: Int = 0,
    val maxValue: Int = 0,
    val totalActivities: List<Activity> = emptyList(),
    val groupedActivities: Map<ActivityType, Int> = emptyMap(),
    val filteredActivities: List<Activity> = emptyList(),
    val randomWords: List<Word> = emptyList(),
    val period: ActivityPeriod = ActivityPeriod.ONE_WEEK,
    val banners: List<Banner> = emptyList(),
)

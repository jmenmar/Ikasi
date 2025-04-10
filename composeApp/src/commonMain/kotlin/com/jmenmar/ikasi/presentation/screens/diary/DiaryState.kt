package com.jmenmar.ikasi.presentation.screens.diary

import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.screens.diary.utils.ActivityPeriod
import com.jmenmar.ikasi.presentation.screens.diary.utils.LevelProgress

data class DiaryState(
    val totalDays: Int = 0,
    val totalActivityDays: Int = 0,
    val maxValue: Int = 0,
    val totalXp: LevelProgress? = null,
    val streak: Int = 0,
    val totalActivities: List<Activity> = emptyList(),
    val groupedActivities: Map<ActivityType, Int> = emptyMap(),
    val filteredActivities: List<Activity> = emptyList(),
    val randomWords: List<Word> = emptyList(),
    val period: ActivityPeriod = ActivityPeriod.ONE_WEEK,
)

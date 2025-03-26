package com.jmenmar.ikasi.presentation.screens.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.screens.diary.utils.ActivityPeriod
import com.jmenmar.ikasi.presentation.screens.diary.utils.calculateLevelAndProgress
import com.jmenmar.ikasi.presentation.screens.diary.utils.calculateStreak
import com.jmenmar.ikasi.presentation.utils.toLocalDate
import com.jmenmar.ikasi.presentation.utils.todayInstant
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus

class DiaryViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(DiaryState())
    val state: StateFlow<DiaryState> = _state

    init {
        getSettings()
        getActivities()
        randomizeWords()
    }

    private fun getSettings() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect { settings ->
                if (settings != null) {
                    _state.value = _state.value.copy(
                        totalDays = settings.startDate.daysUntil(todayLocalDate()) + 1
                    )
                }
            }
        }
    }

    private fun getActivities() {
        viewModelScope.launch {
            ikasiRepository.getAllActivities().collect { allActivities ->
                updateActivitiesState(allActivities)
            }
        }
    }

    private fun updateActivitiesState(allActivities: List<Activity>) {
        val filtered = filterActivitiesByPeriod(allActivities, state.value.period)
        _state.value = state.value.copy(
            totalActivities = allActivities,
            filteredActivities = filtered,
            groupedActivities = groupActivitiesByType(allActivities),
            streak = calculateStreak(allActivities),
            totalXp = calculateLevelAndProgress(totalXp = allActivities.sumOf { it.time }),
            maxValue = filtered.groupBy { it.type }.maxByOrNull { it.value.size }?.value?.size
                ?: 0,
        )
    }

    private fun groupActivitiesByType(allActivities: List<Activity>): Map<ActivityType, Int> {
        return ActivityType.entries.sortedBy { it.priority }.associateWith { activityType ->
            allActivities.filter { it.type == activityType }.sumOf { it.time }
        }
    }

    private fun filterActivitiesByPeriod(allActivities: List<Activity>, period: ActivityPeriod): List<Activity> {
        return allActivities.filter {
            it.date >= todayInstant().minus(
                value = period.value,
                unit = period.unit,
                timeZone = TimeZone.currentSystemDefault()
            ).toLocalDate()
        }
    }

    fun changeActivityPeriod(period: ActivityPeriod) {
        _state.value = _state.value.copy(
            period = period,
        )
        updateActivitiesState(state.value.totalActivities)
    }

    fun randomizeWords() {
        viewModelScope.launch {
            val words = withContext(Dispatchers.IO) {
                ikasiRepository.getRandomWords(length = 3)
            }
            _state.value = _state.value.copy(randomWords = words)
        }
    }
}
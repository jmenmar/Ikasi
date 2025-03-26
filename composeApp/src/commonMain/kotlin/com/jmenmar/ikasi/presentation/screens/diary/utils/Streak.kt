package com.jmenmar.ikasi.presentation.screens.diary.utils

import com.jmenmar.ikasi.domain.model.Activity
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

fun calculateStreak(activities: List<Activity>): Int {
    if (activities.isEmpty()) {
        return 0
    }

    // 1. Get Today's Date
    val today = Clock.System.now().toLocalDateTime(currentSystemDefault()).date

    // 2. Separate Today's and Past Activities
    val todayActivities = activities.filter { it.date == today }
    val pastActivities = activities.filter { it.date < today }

    // 3. Handle Empty Past Activities
    if (pastActivities.isEmpty() && todayActivities.isEmpty()) {
        return 0
    }

    // 4. Sort Past Activities
    val sortedPastActivities = pastActivities
        .map { it.date }
        .distinct()
        .sortedDescending()

    // 5. Initialize Variables
    var streak = 0
    var expectedDate = today.minus(1, DateTimeUnit.DAY) // Start with yesterday
    var previousDate: LocalDate? = null

    // 6. Iterate and Check Past Activities
    for (activityDate in sortedPastActivities) {
        if (activityDate == expectedDate) {
            // Current activity is on the expected date, increment streak
            streak++
            expectedDate = expectedDate.minus(1, DateTimeUnit.DAY) // Move to the previous day
        } else if (activityDate < expectedDate) {
            // Current activity is before the expected date, streak broken
            break
        } else if (activityDate == previousDate) {
            // Current activity is on the same day as the previous one, ignore it
        }
        previousDate = activityDate
    }

    // 7. Check if Today Should Be Included
    if (todayActivities.isNotEmpty()) {
        if (sortedPastActivities.isEmpty() || sortedPastActivities.first() == today.minus(1, DateTimeUnit.DAY)) run {
            streak++
        }
    }

    return streak
}
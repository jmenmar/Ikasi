package com.jmenmar.ikasi.domain.usecase

import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.domain.model.BadgeType
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.screens.diary.utils.calculateLevelAndProgress
import com.jmenmar.ikasi.presentation.screens.diary.utils.calculateStreak
import com.jmenmar.ikasi.presentation.utils.todayLocalDate

class CheckBadgesUseCase(
    private val ikasiRepository: IkasiRepository
) {
    suspend operator fun invoke(flashcardsResult: Int? = null) {
        val activities = ikasiRepository.getActivities()
        val pendingBadges = ikasiRepository.getPendingBadges()
        val words = ikasiRepository.getWords()
        val completedBadges: MutableList<Badge> = mutableListOf()

        pendingBadges.forEach {
            if (checkBadge(activities, words, it, flashcardsResult)) {
                completedBadges.add(it)
            }
        }
        completedBadges.forEach {
            ikasiRepository.completeBadge(it.id)
        }
    }
}

fun checkBadge(
    activities: List<Activity>,
    words: List<Word>,
    badge: Badge,
    flashcardResult: Int? = null
): Boolean {
    return when (badge.type) {
        BadgeType.FIRST_ACTIVITY ->
            isBadgeCompleted(
                value = activities.size,
                total = 1
            )

        BadgeType.FIRST_VOCABULARY ->
            isBadgeCompleted(
                value = words.size,
                total = 1
            )

        BadgeType.ACTIVITIES_FOR_2_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.date == todayLocalDate() }.sumOf { it.time },
                total = 120
            )

        BadgeType.ACTIVITIES_TOTAL_3 ->
            isBadgeCompleted(
                value = activities.filter { it.date == todayLocalDate() }.size,
                total = 3
            )

        BadgeType.ACTIVITIES_TOTAL_5 ->
            isBadgeCompleted(
                value = activities.filter { it.date == todayLocalDate() }.size,
                total = 5
            )

        BadgeType.VOCABULARY_25_ENTRIES ->
            isBadgeCompleted(
                value = words.size,
                total = 25
            )

        BadgeType.VOCABULARY_50_ENTRIES ->
            isBadgeCompleted(
                value = words.size,
                total = 50
            )

        BadgeType.VOCABULARY_100_ENTRIES ->
            isBadgeCompleted(
                value = words.size,
                total = 100
            )

        BadgeType.LEVEL_5 ->
            isBadgeCompleted(
                value = calculateLevelAndProgress(totalXp = activities.sumOf { it.time }).level,
                total = 5
            )

        BadgeType.LEVEL_15 ->
            isBadgeCompleted(
                value = calculateLevelAndProgress(totalXp = activities.sumOf { it.time }).level,
                total = 15
            )

        BadgeType.LEVEL_25 ->
            isBadgeCompleted(
                value = calculateLevelAndProgress(totalXp = activities.sumOf { it.time }).level,
                total = 25
            )

        BadgeType.STREAK_7_DAYS ->
            isBadgeCompleted(
                value = calculateStreak(activities),
                total = 7
            )

        BadgeType.STREAK_15_DAYS ->
            isBadgeCompleted(
                value = calculateStreak(activities),
                total = 15
            )

        BadgeType.STREAK_30_DAYS ->
            isBadgeCompleted(
                value = calculateStreak(activities),
                total = 30
            )

        BadgeType.FLASHCARDS_70 ->
            if (words.size >= 50) {
                isBadgeCompleted(
                    value = flashcardResult ?: 0,
                    total = 70
                )
            } else {
                false
            }

        BadgeType.FLASHCARDS_85 ->
            if (words.size >= 50) {
                isBadgeCompleted(
                    value = flashcardResult ?: 0,
                    total = 85
                )
            } else {
                false
            }

        BadgeType.FLASHCARDS_100 ->
            if (words.size >= 50) {
                isBadgeCompleted(
                    value = flashcardResult ?: 0,
                    total = 100
                )
            } else {
                false
            }

        BadgeType.SPEAKING_25_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.SPEAKING }.sumOf { it.time },
                total = 1500
            )

        BadgeType.SPEAKING_50_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.SPEAKING }.sumOf { it.time },
                total = 3000
            )

        BadgeType.SPEAKING_100_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.SPEAKING }.sumOf { it.time },
                total = 6000
            )

        BadgeType.LISTENING_25_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.LISTENING }.sumOf { it.time },
                total = 1500
            )

        BadgeType.LISTENING_50_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.LISTENING }.sumOf { it.time },
                total = 3000
            )

        BadgeType.LISTENING_100_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.LISTENING }.sumOf { it.time },
                total = 6000
            )

        BadgeType.READING_25_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.READING }.sumOf { it.time },
                total = 1500
            )

        BadgeType.READING_50_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.READING }.sumOf { it.time },
                total = 3000
            )

        BadgeType.READING_100_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.READING }.sumOf { it.time },
                total = 6000
            )

        BadgeType.WRITING_25_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.WRITING }.sumOf { it.time },
                total = 1500
            )

        BadgeType.WRITING_50_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.WRITING }.sumOf { it.time },
                total = 3000
            )

        BadgeType.WRITING_100_HRS ->
            isBadgeCompleted(
                value = activities.filter { it.type == ActivityType.WRITING }.sumOf { it.time },
                total = 6000
            )

        else -> false
    }
}

fun isBadgeCompleted(value: Int, total: Int): Boolean {
    return value >= total
}
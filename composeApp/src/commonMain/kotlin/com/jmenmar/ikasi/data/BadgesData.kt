package com.jmenmar.ikasi.data

import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.domain.model.BadgeType


val badgesData: List<Badge> = listOf(
    Badge(id = 1, type = BadgeType.WELCOME, completed = true),
    Badge(id = 2, type = BadgeType.FIRST_ACTIVITY),
    Badge(id = 3, type = BadgeType.FIRST_VOCABULARY, vocabulary = true),
    Badge(id = 4, type = BadgeType.ACTIVITIES_FOR_2_HRS),
    Badge(id = 5, type = BadgeType.ACTIVITIES_TOTAL_3),
    Badge(id = 6, type = BadgeType.ACTIVITIES_TOTAL_5),
    Badge(id = 7, type = BadgeType.VOCABULARY_25_ENTRIES, vocabulary = true),
    Badge(id = 8, type = BadgeType.VOCABULARY_50_ENTRIES, vocabulary = true),
    Badge(id = 9, type = BadgeType.VOCABULARY_100_ENTRIES, vocabulary = true),
    Badge(id = 10, type = BadgeType.LEVEL_5),
    Badge(id = 11, type = BadgeType.LEVEL_15),
    Badge(id = 12, type = BadgeType.LEVEL_25),
    Badge(id = 13, type = BadgeType.STREAK_7_DAYS),
    Badge(id = 14, type = BadgeType.STREAK_15_DAYS),
    Badge(id = 15, type = BadgeType.STREAK_30_DAYS),
    Badge(id = 16, type = BadgeType.FLASHCARDS_70, vocabulary = true),
    Badge(id = 17, type = BadgeType.FLASHCARDS_85, vocabulary = true),
    Badge(id = 18, type = BadgeType.FLASHCARDS_100, vocabulary = true),
    Badge(id = 19, type = BadgeType.SPEAKING_25_HRS),
    Badge(id = 20, type = BadgeType.SPEAKING_50_HRS),
    Badge(id = 21, type = BadgeType.SPEAKING_100_HRS),
    Badge(id = 22, type = BadgeType.LISTENING_25_HRS),
    Badge(id = 23, type = BadgeType.LISTENING_50_HRS),
    Badge(id = 24, type = BadgeType.LISTENING_100_HRS),
    Badge(id = 25, type = BadgeType.READING_25_HRS),
    Badge(id = 26, type = BadgeType.READING_50_HRS),
    Badge(id = 27, type = BadgeType.READING_100_HRS),
    Badge(id = 28, type = BadgeType.WRITING_25_HRS),
    Badge(id = 29, type = BadgeType.WRITING_50_HRS),
    Badge(id = 30, type = BadgeType.WRITING_100_HRS),
)
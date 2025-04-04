package com.jmenmar.ikasi.domain.model

import androidx.compose.ui.graphics.Color
import com.jmenmar.ikasi.data.model.BadgeEntity
import com.jmenmar.ikasi.ui.Blue
import com.jmenmar.ikasi.ui.BlueMedium
import com.jmenmar.ikasi.ui.Green
import com.jmenmar.ikasi.ui.Orange
import com.jmenmar.ikasi.ui.Pink
import com.jmenmar.ikasi.ui.Yellow
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.badge_2_hrs_in_one_day_description
import ikasi.composeapp.generated.resources.badge_2_hrs_in_one_day_title
import ikasi.composeapp.generated.resources.badge_3_activities_in_one_day_description
import ikasi.composeapp.generated.resources.badge_3_activities_in_one_day_title
import ikasi.composeapp.generated.resources.badge_5_activities_in_one_day_description
import ikasi.composeapp.generated.resources.badge_5_activities_in_one_day_title
import ikasi.composeapp.generated.resources.badge_first_activity_description
import ikasi.composeapp.generated.resources.badge_first_activity_title
import ikasi.composeapp.generated.resources.badge_first_vocabulary_description
import ikasi.composeapp.generated.resources.badge_first_vocabulary_title
import ikasi.composeapp.generated.resources.badge_flashcards_100_description
import ikasi.composeapp.generated.resources.badge_flashcards_100_title
import ikasi.composeapp.generated.resources.badge_flashcards_70_description
import ikasi.composeapp.generated.resources.badge_flashcards_70_title
import ikasi.composeapp.generated.resources.badge_flashcards_85_description
import ikasi.composeapp.generated.resources.badge_flashcards_85_title
import ikasi.composeapp.generated.resources.badge_listening_100_hrs_description
import ikasi.composeapp.generated.resources.badge_listening_100_hrs_title
import ikasi.composeapp.generated.resources.badge_listening_25_hrs_description
import ikasi.composeapp.generated.resources.badge_listening_25_hrs_title
import ikasi.composeapp.generated.resources.badge_listening_50_hrs_description
import ikasi.composeapp.generated.resources.badge_listening_50_hrs_title
import ikasi.composeapp.generated.resources.badge_reach_level_15_description
import ikasi.composeapp.generated.resources.badge_reach_level_15_title
import ikasi.composeapp.generated.resources.badge_reach_level_25_description
import ikasi.composeapp.generated.resources.badge_reach_level_25_title
import ikasi.composeapp.generated.resources.badge_reach_level_5_description
import ikasi.composeapp.generated.resources.badge_reach_level_5_title
import ikasi.composeapp.generated.resources.badge_reading_100_hrs_description
import ikasi.composeapp.generated.resources.badge_reading_100_hrs_title
import ikasi.composeapp.generated.resources.badge_reading_25_hrs_description
import ikasi.composeapp.generated.resources.badge_reading_25_hrs_title
import ikasi.composeapp.generated.resources.badge_reading_50_hrs_description
import ikasi.composeapp.generated.resources.badge_reading_50_hrs_title
import ikasi.composeapp.generated.resources.badge_speaking_100_hrs_description
import ikasi.composeapp.generated.resources.badge_speaking_100_hrs_title
import ikasi.composeapp.generated.resources.badge_speaking_25_hrs_description
import ikasi.composeapp.generated.resources.badge_speaking_25_hrs_title
import ikasi.composeapp.generated.resources.badge_speaking_50_hrs_description
import ikasi.composeapp.generated.resources.badge_speaking_50_hrs_title
import ikasi.composeapp.generated.resources.badge_streak_15_days_description
import ikasi.composeapp.generated.resources.badge_streak_15_days_title
import ikasi.composeapp.generated.resources.badge_streak_30_days_description
import ikasi.composeapp.generated.resources.badge_streak_30_days_title
import ikasi.composeapp.generated.resources.badge_streak_7_days_description
import ikasi.composeapp.generated.resources.badge_streak_7_days_title
import ikasi.composeapp.generated.resources.badge_vocabulary_100_entries_description
import ikasi.composeapp.generated.resources.badge_vocabulary_100_entries_title
import ikasi.composeapp.generated.resources.badge_vocabulary_25_entries_description
import ikasi.composeapp.generated.resources.badge_vocabulary_25_entries_title
import ikasi.composeapp.generated.resources.badge_vocabulary_50_entries_description
import ikasi.composeapp.generated.resources.badge_vocabulary_50_entries_title
import ikasi.composeapp.generated.resources.badge_welcome_description
import ikasi.composeapp.generated.resources.badge_welcome_title
import ikasi.composeapp.generated.resources.badge_writing_100_hrs_description
import ikasi.composeapp.generated.resources.badge_writing_100_hrs_title
import ikasi.composeapp.generated.resources.badge_writing_25_hrs_description
import ikasi.composeapp.generated.resources.badge_writing_25_hrs_title
import ikasi.composeapp.generated.resources.badge_writing_50_hrs_description
import ikasi.composeapp.generated.resources.badge_writing_50_hrs_title
import ikasi.composeapp.generated.resources.ic_arrow_circle_up
import ikasi.composeapp.generated.resources.ic_bolt
import ikasi.composeapp.generated.resources.ic_book
import ikasi.composeapp.generated.resources.ic_check_circle
import ikasi.composeapp.generated.resources.ic_headphones
import ikasi.composeapp.generated.resources.ic_note_stack
import ikasi.composeapp.generated.resources.ic_pencil
import ikasi.composeapp.generated.resources.ic_speak
import ikasi.composeapp.generated.resources.ic_waving_hand
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class Badge(
    val id: Int,
    val type: BadgeType,
    val completed: Boolean = false,
    val vocabulary: Boolean = false,
    val date: Int? = null,
) {
    companion object {
        fun Badge.toEntity(): BadgeEntity {
            return BadgeEntity(
                id = id,
                type = type,
                completed = completed,
                vocabulary = vocabulary,
                date = date,
            )
        }
    }
}

enum class BadgeType(
    val icon: DrawableResource,
    val color: Color,
    val title: StringResource,
    val description: StringResource
) {
    WELCOME(
        icon = Res.drawable.ic_waving_hand,
        color = BlueMedium,
        title = Res.string.badge_welcome_title,
        description = Res.string.badge_welcome_description,
    ),
    FIRST_ACTIVITY(
        icon = Res.drawable.ic_check_circle,
        color = BlueMedium,
        title = Res.string.badge_first_activity_title,
        description = Res.string.badge_first_activity_description,
    ),
    FIRST_VOCABULARY(
        icon = Res.drawable.ic_note_stack,
        color = BlueMedium,
        title = Res.string.badge_first_vocabulary_title,
        description = Res.string.badge_first_vocabulary_description,
    ),
    ACTIVITIES_FOR_2_HRS(
        icon = Res.drawable.ic_check_circle,
        color = BlueMedium,
        title = Res.string.badge_2_hrs_in_one_day_title,
        description = Res.string.badge_2_hrs_in_one_day_description,
    ),
    ACTIVITIES_TOTAL_3(
        icon = Res.drawable.ic_check_circle,
        color = BlueMedium,
        title = Res.string.badge_3_activities_in_one_day_title,
        description = Res.string.badge_3_activities_in_one_day_description
    ),
    ACTIVITIES_TOTAL_5(
        icon = Res.drawable.ic_check_circle,
        color = BlueMedium,
        title = Res.string.badge_5_activities_in_one_day_title,
        description = Res.string.badge_5_activities_in_one_day_description
    ),
    VOCABULARY_25_ENTRIES(
        icon = Res.drawable.ic_note_stack,
        color = Pink,
        title = Res.string.badge_vocabulary_25_entries_title,
        description = Res.string.badge_vocabulary_25_entries_description,
    ),
    VOCABULARY_50_ENTRIES(
        icon = Res.drawable.ic_note_stack,
        color = Pink,
        title = Res.string.badge_vocabulary_50_entries_title,
        description = Res.string.badge_vocabulary_50_entries_description,
    ),
    VOCABULARY_100_ENTRIES(
        icon = Res.drawable.ic_note_stack,
        color = Pink,
        title = Res.string.badge_vocabulary_100_entries_title,
        description = Res.string.badge_vocabulary_100_entries_description,
    ),
    LEVEL_5(
        icon = Res.drawable.ic_arrow_circle_up,
        color = BlueMedium,
        title = Res.string.badge_reach_level_5_title,
        description = Res.string.badge_reach_level_5_description,
    ),
    LEVEL_15(
        icon = Res.drawable.ic_arrow_circle_up,
        color = BlueMedium,
        title = Res.string.badge_reach_level_15_title,
        description = Res.string.badge_reach_level_15_description,
    ),
    LEVEL_25(
        icon = Res.drawable.ic_arrow_circle_up,
        color = BlueMedium,
        title = Res.string.badge_reach_level_25_title,
        description = Res.string.badge_reach_level_25_description,
    ),
    STREAK_7_DAYS(
        icon = Res.drawable.ic_bolt,
        color = BlueMedium,
        title = Res.string.badge_streak_7_days_title,
        description = Res.string.badge_streak_7_days_description,
    ),
    STREAK_15_DAYS(
        icon = Res.drawable.ic_bolt,
        color = BlueMedium,
        title = Res.string.badge_streak_15_days_title,
        description = Res.string.badge_streak_15_days_description,
    ),
    STREAK_30_DAYS(
        icon = Res.drawable.ic_bolt,
        color = BlueMedium,
        title = Res.string.badge_streak_30_days_title,
        description = Res.string.badge_streak_30_days_description,
    ),
    FLASHCARDS_70(
        icon = Res.drawable.ic_note_stack,
        color = Pink,
        title = Res.string.badge_flashcards_70_title,
        description = Res.string.badge_flashcards_70_description,
    ),
    FLASHCARDS_85(
        icon = Res.drawable.ic_note_stack,
        color = Pink,
        title = Res.string.badge_flashcards_85_title,
        description = Res.string.badge_flashcards_85_description,
    ),
    FLASHCARDS_100(
        icon = Res.drawable.ic_note_stack,
        color = Pink,
        title = Res.string.badge_flashcards_100_title,
        description = Res.string.badge_flashcards_100_description,
    ),
    SPEAKING_25_HRS(
        icon = Res.drawable.ic_speak,
        color = Orange,
        title = Res.string.badge_speaking_25_hrs_title,
        description = Res.string.badge_speaking_25_hrs_description,
    ),
    SPEAKING_50_HRS(
        icon = Res.drawable.ic_speak,
        color = Orange,
        title = Res.string.badge_speaking_50_hrs_title,
        description = Res.string.badge_speaking_50_hrs_description,
    ),
    SPEAKING_100_HRS(
        icon = Res.drawable.ic_speak,
        color = Orange,
        title = Res.string.badge_speaking_100_hrs_title,
        description = Res.string.badge_speaking_100_hrs_description,
    ),
    LISTENING_25_HRS(
        icon = Res.drawable.ic_headphones,
        color = Yellow,
        title = Res.string.badge_listening_25_hrs_title,
        description = Res.string.badge_listening_25_hrs_description,
    ),
    LISTENING_50_HRS(
        icon = Res.drawable.ic_headphones,
        color = Yellow,
        title = Res.string.badge_listening_50_hrs_title,
        description = Res.string.badge_listening_50_hrs_description,
    ),
    LISTENING_100_HRS(
        icon = Res.drawable.ic_headphones,
        color = Yellow,
        title = Res.string.badge_listening_100_hrs_title,
        description = Res.string.badge_listening_100_hrs_description,
    ),
    READING_25_HRS(
        icon = Res.drawable.ic_book,
        color = Green,
        title = Res.string.badge_reading_25_hrs_title,
        description = Res.string.badge_reading_25_hrs_description,
    ),
    READING_50_HRS(
        icon = Res.drawable.ic_book,
        color = Green,
        title = Res.string.badge_reading_50_hrs_title,
        description = Res.string.badge_reading_50_hrs_description,
    ),
    READING_100_HRS(
        icon = Res.drawable.ic_book,
        color = Green,
        title = Res.string.badge_reading_100_hrs_title,
        description = Res.string.badge_reading_100_hrs_description,
    ),
    WRITING_25_HRS(
        icon = Res.drawable.ic_pencil,
        color = Blue,
        title = Res.string.badge_writing_25_hrs_title,
        description = Res.string.badge_writing_25_hrs_description,
    ),
    WRITING_50_HRS(
        icon = Res.drawable.ic_pencil,
        color = Blue,
        title = Res.string.badge_writing_50_hrs_title,
        description = Res.string.badge_writing_50_hrs_description,
    ),
    WRITING_100_HRS(
        icon = Res.drawable.ic_pencil,
        color = Blue,
        title = Res.string.badge_writing_100_hrs_title,
        description = Res.string.badge_writing_100_hrs_description,
    )
}

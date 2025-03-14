package com.jmenmar.ikasi.domain.model

import com.jmenmar.ikasi.data.model.ActivityEntity
import kotlinx.datetime.LocalDate

data class Activity(
    val type: ActivityType,
    val date: LocalDate,
    val time: Int,
) {
    companion object {
        fun Activity.toEntity() = ActivityEntity(
            type = type,
            date = date.toEpochDays(),
            time = time,
        )
    }
}

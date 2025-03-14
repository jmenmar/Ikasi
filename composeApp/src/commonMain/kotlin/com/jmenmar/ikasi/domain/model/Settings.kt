package com.jmenmar.ikasi.domain.model

import com.jmenmar.ikasi.data.model.SettingsEntity
import com.jmenmar.ikasi.presentation.onboarding.GoalPeriod
import kotlinx.datetime.LocalDate

data class Settings(
    val onboarding: Boolean,
    val startDate: LocalDate,
    val period: GoalPeriod,
) {
    companion object {
        fun Settings.toEntity() = SettingsEntity(
            onboarding = onboarding,
            startDate = startDate.toEpochDays(),
            period = period,
        )
    }
}

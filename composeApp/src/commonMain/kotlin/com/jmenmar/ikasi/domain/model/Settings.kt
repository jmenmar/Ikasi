package com.jmenmar.ikasi.domain.model

import com.jmenmar.ikasi.data.model.SettingsEntity
import kotlinx.datetime.LocalDate

data class Settings(
    val onboarding: Boolean,
    val startDate: LocalDate,
) {
    companion object {
        fun Settings.toEntity() = SettingsEntity(
            onboarding = onboarding,
            startDate = startDate.toEpochDays(),
        )
    }
}

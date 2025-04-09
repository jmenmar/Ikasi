package com.jmenmar.ikasi.domain.model

import com.jmenmar.ikasi.data.model.SettingsEntity
import kotlinx.datetime.LocalDate

data class Settings(
    val onboarding: Boolean,
    val startDate: LocalDate,
    val darkTheme: Boolean = true,
) {
    companion object {
        fun Settings.toEntity() = SettingsEntity(
            onboarding = onboarding,
            startDate = startDate.toEpochDays(),
            darkTheme = darkTheme,
        )
    }
}

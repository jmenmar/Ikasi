package com.jmenmar.ikasi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmenmar.ikasi.domain.model.Settings
import kotlinx.datetime.LocalDate

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey
    val id: Int = 0,
    val onboarding: Boolean = true,
    val startDate: Int,
) {
    companion object {
        fun SettingsEntity.toDomain() = Settings(
            onboarding = onboarding,
            startDate = LocalDate.fromEpochDays(this.startDate),
        )
    }
}
package com.jmenmar.ikasi.domain.usecase

import com.jmenmar.ikasi.data.badgesData
import com.jmenmar.ikasi.domain.model.Settings
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.todayLocalDate

class StartingUseCase(
    private val ikasiRepository: IkasiRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        if (ikasiRepository.getSettings() == null) {
            ikasiRepository.newSettings(
                settings = Settings(
                    onboarding = false,
                    startDate = todayLocalDate()
                )
            ).getOrThrow()
        } else {
            ikasiRepository.updateSettings(
                onboarding = false,
                date = todayLocalDate().toEpochDays()
            ).getOrThrow()
        }
        ikasiRepository.newBadges(badgesData).getOrThrow()
        return Result.success(true)
    }
}
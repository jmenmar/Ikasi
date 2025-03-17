package com.jmenmar.ikasi.domain.usecase

import com.jmenmar.ikasi.domain.repository.IkasiRepository

class DeleteAllUseCase(
    private val ikasiRepository: IkasiRepository
) {
    suspend operator fun invoke(deleteVocabulary: Boolean): Result<Boolean> {
        if (deleteVocabulary) {
            ikasiRepository.deleteAllWords().getOrThrow()

        }
        ikasiRepository.deleteAllActivities().getOrThrow()
        ikasiRepository.deleteSettings().getOrThrow()
        return Result.success(true)
    }
}
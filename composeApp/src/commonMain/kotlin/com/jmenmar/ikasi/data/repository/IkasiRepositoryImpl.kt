package com.jmenmar.ikasi.data.repository

import com.jmenmar.ikasi.data.database.IkasiDatabase
import com.jmenmar.ikasi.data.model.ActivityEntity.Companion.toDomain
import com.jmenmar.ikasi.data.model.SettingsEntity.Companion.toDomain
import com.jmenmar.ikasi.data.model.WordEntity.Companion.toDomain
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.Activity.Companion.toEntity
import com.jmenmar.ikasi.domain.model.Settings
import com.jmenmar.ikasi.domain.model.Settings.Companion.toEntity
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.domain.model.Word.Companion.toEntity
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import kotlinx.coroutines.flow.map

class IkasiRepositoryImpl(
    private val ikasiDatabase: IkasiDatabase,
) : IkasiRepository {
    override suspend fun newActivity(activity: Activity): Result<Boolean> {
        return try {
            ikasiDatabase.activityDao().insertActivity(activity = activity.toEntity())
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getActivities(dateFrom: Int, dateTo: Int) =
        ikasiDatabase.activityDao()
            .getActivities(dateFrom = dateFrom, dateTo = dateTo).map { list ->
                list.map { activity ->
                    activity.toDomain()
                }
            }

    override suspend fun getAllActivities() =
        ikasiDatabase.activityDao().getAllActivities().map { list ->
            list.map { activity ->
                activity.toDomain()
            }
        }

    override suspend fun deleteActivity(activity: Activity): Result<Boolean> {
        return try {
            ikasiDatabase.activityDao().deleteActivity(
                type = activity.type,
                date = activity.toEntity().date
            )
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteAllActivities(): Result<Boolean> {
        return try {
            ikasiDatabase.activityDao().deleteAllActivities()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllWords() =
        ikasiDatabase.wordDao().getAllWords().map {
            it.map { word ->
                word.toDomain()
            }.sortedBy { word ->
                word.title
            }
        }

    override suspend fun newWord(word: Word): Result<Boolean> {
        return try {
            ikasiDatabase.wordDao().insertWord(word = word.toEntity())
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteWord(word: Word): Result<Boolean> {
        return try {
            ikasiDatabase.wordDao().deleteWord(title = word.title)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteAllWords(): Result<Boolean> {
        return try {
            ikasiDatabase.wordDao().deleteAllWords()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRandomWords(length: Int): List<Word> {
        return try {
            val words = ikasiDatabase.wordDao().getRandomWords(length = length).map {
                it.toDomain()
            }
            return if (words.size < length) { emptyList() } else { words }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getSettings() =
        ikasiDatabase.settingsDao().getSettings().map { it?.toDomain() }

    override suspend fun newSettings(settings: Settings): Result<Boolean> {
        return try {
            ikasiDatabase.settingsDao().insertWord(settings = settings.toEntity())
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteSettings(): Result<Boolean> {
        return try {
            ikasiDatabase.settingsDao().deleteSettings()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
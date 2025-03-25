package com.jmenmar.ikasi.domain.repository

import com.jmenmar.ikasi.data.model.ActivityEntity
import com.jmenmar.ikasi.data.model.SettingsEntity
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.Settings
import com.jmenmar.ikasi.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface IkasiRepository {
    suspend fun newActivity(activity: Activity): Result<Boolean>
    suspend fun getActivities(dateFrom: Int, dateTo: Int): Flow<List<Activity>>
    suspend fun getAllActivities(): Flow<List<Activity>>
    suspend fun deleteActivity(activity: Activity): Result<Boolean>
    suspend fun deleteAllActivities(): Result<Boolean>

    suspend fun getAllWords(): Flow<List<Word>>
    suspend fun newWord(word: Word): Result<Boolean>
    suspend fun deleteWord(word: Word): Result<Boolean>
    suspend fun deleteAllWords(): Result<Boolean>
    suspend fun getRandomWords(length: Int): List<Word>

    suspend fun getSettings(): Flow<Settings?>
    suspend fun newSettings(settings: Settings): Result<Boolean>
    suspend fun deleteSettings(): Result<Boolean>

    suspend fun newActivity(activity: ActivityEntity): Result<Boolean>
    suspend fun newSettings(settings: SettingsEntity): Result<Boolean>
}

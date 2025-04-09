package com.jmenmar.ikasi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmenmar.ikasi.data.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(settings: SettingsEntity)

    // SELECT
    @Query("SELECT * FROM settings WHERE id = 0")
    fun getSettings(): Flow<SettingsEntity?>

    @Query("SELECT * FROM settings WHERE id = 0")
    suspend fun getSettings2(): SettingsEntity?

    // UPDATE
    @Query("UPDATE settings SET onboarding = :onboarding, startDate = :date WHERE id = 0")
    suspend fun updateSettings(onboarding: Boolean, date: Int)

    // DELETE
    @Query("DELETE FROM settings WHERE id = 0")
    suspend fun deleteSettings()
}
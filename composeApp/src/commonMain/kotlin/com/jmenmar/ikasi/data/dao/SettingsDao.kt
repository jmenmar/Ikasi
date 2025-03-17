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

    // DELETE
    @Query("DELETE FROM settings WHERE id = 0")
    suspend fun deleteSettings()
}
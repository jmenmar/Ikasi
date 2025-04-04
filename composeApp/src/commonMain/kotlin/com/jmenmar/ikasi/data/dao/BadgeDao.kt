package com.jmenmar.ikasi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jmenmar.ikasi.data.model.BadgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {
    // INSERT
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBadge(badge: BadgeEntity)

    // SELECT
    @Query("SELECT * FROM badge")
    fun getAllBadges(): Flow<List<BadgeEntity>>

    @Query("SELECT * FROM badge WHERE completed = 1 AND date IS NULL")
    fun getNewCompletedBadges(): Flow<List<BadgeEntity>>

    @Query("SELECT * FROM badge WHERE completed = 0")
    suspend fun getPendingBadges(): List<BadgeEntity>

    // UPDATE
    @Update
    suspend fun updateBadge(badge: BadgeEntity)

    @Query("UPDATE badge SET completed = 1 WHERE id = :badgeId")
    suspend fun completeBadge(badgeId: Int)

    // DELETE
    @Query("DELETE FROM badge")
    suspend fun deleteAllBadges()

    @Query("DELETE FROM badge WHERE vocabulary = 0")
    suspend fun deleteAllBadgesExceptVocabulary()
}
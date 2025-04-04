package com.jmenmar.ikasi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.domain.model.BadgeType

@Entity(tableName = "badge")
data class BadgeEntity(
    @PrimaryKey
    val id: Int,
    val type: BadgeType,
    val completed: Boolean,
    val vocabulary: Boolean,
    val date: Int? = null,
) {
    companion object {
        fun BadgeEntity.toDomain(): Badge {
            return Badge(
                id = id,
                type = type,
                completed = completed,
                vocabulary = vocabulary,
                date = date,
            )
        }
    }
}

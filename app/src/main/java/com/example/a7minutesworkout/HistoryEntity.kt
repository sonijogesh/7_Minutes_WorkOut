package com.example.a7minutesworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-name")
data class HistoryEntity(
    @PrimaryKey
    val date : String
)

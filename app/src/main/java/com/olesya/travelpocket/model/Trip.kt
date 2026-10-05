package com.olesya.travelpocket.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val city: String,
    val country: String,
    val startDate: String,
    val endDate: String,
    val budget: Int,
    val spent: Int = 0,
    val status: String = "Запланировано"
)

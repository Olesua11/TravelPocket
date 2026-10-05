package com.olesya.travelpocket.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.olesya.travelpocket.model.Trip
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Query("SELECT * FROM trips ORDER BY id DESC")
    fun observeTrips(): Flow<List<Trip>>

    @Insert
    suspend fun insert(trip: Trip)

    @Query("SELECT * FROM trips WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): Trip?
}

package com.olesya.travelpocket.data

import com.olesya.travelpocket.model.Trip

class TripRepository(private val dao: TripDao) {
    val trips = dao.observeTrips()
    suspend fun add(trip: Trip) = dao.insert(trip)
    suspend fun get(id: Long) = dao.getById(id)
}

package com.olesya.travelpocket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.olesya.travelpocket.data.AppDatabase
import com.olesya.travelpocket.data.TripRepository
import com.olesya.travelpocket.model.Trip
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TripsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TripRepository(AppDatabase.get(application).tripDao())

    val trips = repository.trips.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun addTrip(city: String, country: String, start: String, end: String, budget: Int) {
        viewModelScope.launch {
            repository.add(Trip(city = city, country = country, startDate = start, endDate = end, budget = budget))
        }
    }

    fun seedIfEmpty() {
        viewModelScope.launch {
            if (trips.value.isEmpty()) {
                repository.add(Trip(city = "Стамбул", country = "Турция", startDate = "12 окт", endDate = "17 окт", budget = 85000, spent = 26400, status = "Через 7 дней"))
                repository.add(Trip(city = "Тбилиси", country = "Грузия", startDate = "02 ноя", endDate = "08 ноя", budget = 70000, spent = 0, status = "Запланировано"))
            }
        }
    }
}

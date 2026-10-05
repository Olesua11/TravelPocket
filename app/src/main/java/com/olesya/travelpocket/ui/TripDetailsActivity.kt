package com.olesya.travelpocket.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.olesya.travelpocket.data.AppDatabase
import com.olesya.travelpocket.databinding.ActivityTripDetailsBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class TripDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTripDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTripDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener { finish() }

        val id = intent.getLongExtra("tripId", -1)
        lifecycleScope.launch {
            val trip = AppDatabase.get(this@TripDetailsActivity).tripDao().getById(id) ?: return@launch
            binding.city.text = trip.city
            binding.country.text = trip.country
            binding.dates.text = "${trip.startDate} — ${trip.endDate}"
            binding.status.text = trip.status
            val f = NumberFormat.getNumberInstance(Locale("ru", "RU"))
            binding.totalBudget.text = "${f.format(trip.budget)} ₽"
            binding.spent.text = "${f.format(trip.spent)} ₽"
            binding.remaining.text = "${f.format(trip.budget - trip.spent)} ₽"
        }
    }
}

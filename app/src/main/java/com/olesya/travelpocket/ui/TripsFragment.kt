package com.olesya.travelpocket.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.olesya.travelpocket.adapter.TripAdapter
import com.olesya.travelpocket.databinding.DialogAddTripBinding
import com.olesya.travelpocket.databinding.FragmentTripsBinding
import com.olesya.travelpocket.viewmodel.TripsViewModel
import kotlinx.coroutines.launch

class TripsFragment : Fragment() {
    private var _binding: FragmentTripsBinding? = null
    private val binding get() = _binding!!
    private val vm: TripsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        _binding = FragmentTripsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, state: Bundle?) {
        val adapter = TripAdapter { trip ->
            startActivity(Intent(requireContext(), TripDetailsActivity::class.java).putExtra("tripId", trip.id))
        }
        binding.tripList.layoutManager = LinearLayoutManager(requireContext())
        binding.tripList.adapter = adapter
        binding.addTrip.setOnClickListener { showAddDialog() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.trips.collect {
                    adapter.submitList(it)
                    binding.emptyState.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
        vm.seedIfEmpty()
    }

    private fun showAddDialog() {
        val b = DialogAddTripBinding.inflate(layoutInflater)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Новая поездка")
            .setView(b.root)
            .setNegativeButton("Отмена", null)
            .setPositiveButton("Добавить") { _, _ ->
                val city = b.city.text.toString().trim()
                if (city.isNotEmpty()) {
                    vm.addTrip(
                        city,
                        b.country.text.toString().ifBlank { "—" },
                        b.startDate.text.toString().ifBlank { "Дата" },
                        b.endDate.text.toString().ifBlank { "Дата" },
                        b.budget.text.toString().toIntOrNull() ?: 0
                    )
                }
            }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView(); _binding = null
    }
}

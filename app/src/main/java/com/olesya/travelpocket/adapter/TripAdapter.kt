package com.olesya.travelpocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olesya.travelpocket.databinding.ItemTripBinding
import com.olesya.travelpocket.model.Trip
import java.text.NumberFormat
import java.util.Locale

class TripAdapter(private val onClick: (Trip) -> Unit) : ListAdapter<Trip, TripAdapter.VH>(Diff) {
    object Diff : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Trip, newItem: Trip) = oldItem == newItem
    }

    inner class VH(private val b: ItemTripBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: Trip) = with(b) {
            city.text = item.city
            country.text = item.country
            dates.text = "${item.startDate} — ${item.endDate}"
            status.text = item.status
            budget.text = "Бюджет ${NumberFormat.getNumberInstance(Locale("ru", "RU")).format(item.budget)} ₽"
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
}

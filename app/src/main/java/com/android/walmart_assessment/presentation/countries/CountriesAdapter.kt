package com.android.walmart_assessment.presentation.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.walmart_assessment.data.Country
import com.android.walmart_assessment.databinding.ItemCountryBinding

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountryVH>() {

    var items: List<Country> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVH {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryVH(binding.root, binding)
    }

    override fun onBindViewHolder(holder: CountryVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CountryVH(
        view: View,
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(view) {
        fun bind(country: Country) {
            binding.country = country
        }
    }
}
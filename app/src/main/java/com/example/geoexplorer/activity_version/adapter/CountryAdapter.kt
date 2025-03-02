package com.example.geoexplorer.activity_version.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geoexplorer.R
import com.example.geoexplorer.activity_version.model.Country

class CountryAdapter(
    private val countryList: List<Country>,
    private val onItemClick: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.country_name)
        val countryFlag: ImageView = itemView.findViewById(R.id.country_flag)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrow_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.countryName.text = country.name
        holder.countryFlag.setImageResource(country.flagResId)

        // ✅ Ajout d'un effet de clic avec animation
        holder.itemView.setOnClickListener {
            it.alpha = 0.6f
            it.postDelayed({ it.alpha = 1f }, 200)
            onItemClick(country)
        }
    }

    override fun getItemCount(): Int = countryList.size
}

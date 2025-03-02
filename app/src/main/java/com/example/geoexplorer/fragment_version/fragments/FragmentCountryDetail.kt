package com.example.geoexplorer.fragment_version.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.geoexplorer.R
import com.example.geoexplorer.fragment_version.model.Country

class FragmentCountryDetail : Fragment() {

    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            country = it.getSerializable("country") as Country
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flagImageView: ImageView = view.findViewById(R.id.country_detail_flag)
        val nameTextView: TextView = view.findViewById(R.id.country_detail_name)
        val capitalTextView: TextView = view.findViewById(R.id.country_capital)
        val populationTextView: TextView = view.findViewById(R.id.country_population)
        val presidentTextView: TextView = view.findViewById(R.id.country_president)
        val pibTextView: TextView = view.findViewById(R.id.country_pib)
        val languageTextView: TextView = view.findViewById(R.id.country_language)
        val currencyTextView: TextView = view.findViewById(R.id.country_currency)

        // ✅ Vérifie que les données ne sont pas nulles avant de les afficher
        flagImageView.setImageResource(country.flagResId)
        nameTextView.text = country.name
        capitalTextView.text = "Capitale : ${country.capital}"
        populationTextView.text = "Population : ${country.population}"
        presidentTextView.text = "Président : ${country.president}"
        pibTextView.text = "PIB : ${country.pib}"
        languageTextView.text = "Langue : ${country.language}"
        currencyTextView.text = "Monnaie : ${country.currency}"
    }

    companion object {
        fun newInstance(country: Country): FragmentCountryDetail {
            val fragment = FragmentCountryDetail()
            val args = Bundle()
            args.putSerializable("country", country)
            fragment.arguments = args
            return fragment
        }
    }
}

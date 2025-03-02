package com.example.geoexplorer.fragment_version.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geoexplorer.R

import com.example.geoexplorer.fragment_version.FragmentActivity
import com.example.geoexplorer.fragment_version.adapter.CountryAdapter
import com.example.geoexplorer.fragment_version.model.Country


class FragmentCountryList : Fragment() {

    private lateinit var countryAdapter: CountryAdapter
    private var listener: FragmentActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActivity) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val countries: List<Country> = listOf(
            Country("Qatar", R.drawable.flag_qatar, 25.276987, 51.520008, "QA",
                "Doha", "2.8M", "Tamim ben Hamad Al Thani", "206 milliards USD", "Arabe", "Riyal Qatari"),

            Country("Palestine", R.drawable.flag_palestine, 31.9522, 35.2332, "PS",
                "Jérusalem-Est / Ramallah", "7.09M", "Mahmoud Abbas", "14 milliards USD", "Arabe", "Shekel"),

            Country("Algérie", R.drawable.flag_algeria, 28.0339, 1.6596, "DZ",
                "Alger", "44.2M", "Abdelmadjid Tebboune", "170 milliards USD", "Arabe, Tamazight", "Dinar Algérien"),

            Country("France", R.drawable.flag_france, 48.8566, 2.3522, "FR",
                "Paris", "67.5M", "Emmanuel Macron", "2.8 trillions USD", "Français", "Euro"),

            Country("Nouvelle-Zélande", R.drawable.flag_newzealand, -40.9006, 174.8860, "NZ",
                "Wellington", "5.1M", "Chris Hipkins", "250 milliards USD", "Anglais, Māori", "Dollar Néo-Zélandais"),

            Country("Russie", R.drawable.flag_russia, 55.7558, 37.6173, "RU",
                "Moscou", "143.4M", "Vladimir Poutine", "1.5 trillions USD", "Russe", "Rouble Russe"),

            Country("Japon", R.drawable.flag_japan, 35.682839, 139.759455, "JP",
                "Tokyo", "125.8M", "Fumio Kishida", "5.1 trillions USD", "Japonais", "Yen Japonais"),

            Country("Singapour", R.drawable.flag_singapore, 1.3521, 103.8198, "SG",
                "Singapour", "5.7M", "Tharman Shanmugaratnam", "466 milliards USD", "Anglais, Mandarin, Malais, Tamoul", "Dollar de Singapour")
        )


        countryAdapter = CountryAdapter(countries) { selectedCountry ->
            listener?.showCountryDetails(selectedCountry)
        }

        recyclerView.adapter = countryAdapter
    }
}

package com.example.geoexplorer.activity_version

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geoexplorer.R
import com.example.geoexplorer.activity_version.adapter.CountryAdapter
import com.example.geoexplorer.activity_version.model.Country



class CountryListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

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
            val intent = Intent(this, CountryDetailActivity::class.java)

            intent.putExtra("countryName", selectedCountry.name)
            intent.putExtra("countryFlag", selectedCountry.flagResId)
            intent.putExtra("latitude", selectedCountry.latitude)
            intent.putExtra("longitude", selectedCountry.longitude)
            intent.putExtra("capital", selectedCountry.capital)
            intent.putExtra("population", selectedCountry.population)
            intent.putExtra("president", selectedCountry.president)
            intent.putExtra("pib", selectedCountry.pib)
            intent.putExtra("language", selectedCountry.language)
            intent.putExtra("currency", selectedCountry.currency)

            startActivity(intent)
        }


        recyclerView.adapter = countryAdapter
    }
}

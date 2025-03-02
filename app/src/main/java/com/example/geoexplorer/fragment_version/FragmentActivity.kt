package com.example.geoexplorer.fragment_version


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoexplorer.R
import com.example.geoexplorer.fragment_version.fragments.FragmentCountryDetail
import com.example.geoexplorer.fragment_version.fragments.FragmentCountryList
import com.example.geoexplorer.fragment_version.model.Country


class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentCountryList())
                .commit()
        }
    }

    fun showCountryDetails(country: Country) {  // ✅ Corrigé
        val fragment = FragmentCountryDetail.newInstance(country)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

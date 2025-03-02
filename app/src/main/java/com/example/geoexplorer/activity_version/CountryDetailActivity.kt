package com.example.geoexplorer.activity_version

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.geoexplorer.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class CountryDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var countryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        // Récupérer les données passées via Intent
        countryName = intent.getStringExtra("countryName") ?: "Inconnu"
        val flagResId = intent.getIntExtra("countryFlag", R.drawable.ic_launcher_foreground)
        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)

        val capital = intent.getStringExtra("capital") ?: "Non disponible"
        val population = intent.getStringExtra("population") ?: "Non disponible"
        val president = intent.getStringExtra("president") ?: "Non disponible"
        val pib = intent.getStringExtra("pib") ?: "Non disponible"
        val language = intent.getStringExtra("language") ?: "Non disponible"
        val currency = intent.getStringExtra("currency") ?: "Non disponible"

        // Mise à jour des vues
        findViewById<ImageView>(R.id.country_detail_flag).setImageResource(flagResId)
        findViewById<TextView>(R.id.country_detail_name).text = countryName
        findViewById<TextView>(R.id.country_capital).text = "Capitale : $capital"
        findViewById<TextView>(R.id.country_population).text = "Population : $population"
        findViewById<TextView>(R.id.country_president).text = "Président : $president"
        findViewById<TextView>(R.id.country_pib).text = "PIB : $pib"
        findViewById<TextView>(R.id.country_language).text = "Langue : $language"
        findViewById<TextView>(R.id.country_currency).text = "Monnaie : $currency"

        // Initialisation de la carte
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val countryLocation = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(countryLocation).title(countryName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(countryLocation, 5f))
    }
}

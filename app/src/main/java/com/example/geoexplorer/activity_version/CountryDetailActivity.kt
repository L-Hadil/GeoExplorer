package com.example.geoexplorer.activity_version

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.geoexplorer.BuildConfig
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

        Log.d("MAPS_API_KEY", "Clé API utilisée: ${BuildConfig.MAPS_API_KEY}")

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

        findViewById<ImageView>(R.id.country_detail_flag).setImageResource(flagResId)
        findViewById<TextView>(R.id.country_detail_name).text = countryName
        findViewById<TextView>(R.id.country_capital).text = "Capitale : $capital"
        findViewById<TextView>(R.id.country_population).text = "Population : $population"
        findViewById<TextView>(R.id.country_president).text = "Président : $president"
        findViewById<TextView>(R.id.country_pib).text = "PIB : $pib"
        findViewById<TextView>(R.id.country_language).text = "Langue : $language"
        findViewById<TextView>(R.id.country_currency).text = "Monnaie : $currency"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        if (mapFragment != null) {
            mapFragment.getMapAsync(this)
        } else {
            Log.e("GoogleMaps", "Erreur: Impossible de charger SupportMapFragment")
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (latitude == 0.0 && longitude == 0.0) {
            Log.e("GoogleMaps", "Coordonnées invalides: latitude = $latitude, longitude = $longitude")
            return
        }

        Log.d("GoogleMaps", "Carte prête: Latitude = $latitude, Longitude = $longitude")

        val countryLocation = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(countryLocation).title(countryName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(countryLocation, 5f))
    }
}

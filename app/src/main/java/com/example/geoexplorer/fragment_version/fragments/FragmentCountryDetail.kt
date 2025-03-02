package com.example.geoexplorer.fragment_version.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.geoexplorer.BuildConfig
import com.example.geoexplorer.R
import com.example.geoexplorer.fragment_version.model.Country
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FragmentCountryDetail : Fragment(), OnMapReadyCallback {

    private lateinit var country: Country
    private lateinit var googleMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var countryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            country = it.getSerializable("country") as Country
            countryName = country.name
            latitude = country.latitude
            longitude = country.longitude
        }

        Log.d("MAPS_API_KEY", "Clé API utilisée: ${BuildConfig.MAPS_API_KEY}")
        Log.d("GoogleMaps", "Données reçues - Pays: $countryName, Latitude: $latitude, Longitude: $longitude")
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

        flagImageView.setImageResource(country.flagResId)
        nameTextView.text = country.name
        capitalTextView.text = "Capitale : ${country.capital}"
        populationTextView.text = "Population : ${country.population}"
        presidentTextView.text = "Président : ${country.president}"
        pibTextView.text = "PIB : ${country.pib}"
        languageTextView.text = "Langue : ${country.language}"
        currencyTextView.text = "Monnaie : ${country.currency}"

        // ✅ Initialiser Google Maps en vérifiant si `mapFragment` est bien trouvé
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
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

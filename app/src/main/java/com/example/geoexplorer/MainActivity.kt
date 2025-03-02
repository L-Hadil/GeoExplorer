package com.example.geoexplorer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.geoexplorer.activity_version.CountryListActivity
import com.example.geoexplorer.fragment_version.FragmentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupérer les boutons
        val btnActivityVersion = findViewById<Button>(R.id.btnActivityVersion)
        val btnFragmentVersion = findViewById<Button>(R.id.btnFragmentVersion)

        // Lancer l'activité avec la liste des pays (Activités)
        btnActivityVersion.setOnClickListener {
            val intent = Intent(this, CountryListActivity::class.java)
            startActivity(intent)
        }

        // Lancer l'activité avec les fragments
        btnFragmentVersion.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }
}

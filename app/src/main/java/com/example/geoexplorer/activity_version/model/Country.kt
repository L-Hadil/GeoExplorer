package com.example.geoexplorer.activity_version.model

data class Country(
    val name: String,
    val flagResId: Int,
    val latitude: Double,
    val longitude: Double,
    val isoCode: String,
    val capital: String,
    val population: String,
    val president: String,
    val pib: String,
    val language: String,
    val currency: String
)

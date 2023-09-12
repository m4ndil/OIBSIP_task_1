package com.example.uc

class Unit {
    data class Conversion(
        val unitName: String,
        val fromUnit: String,
        val toUnit: String,
        val conversionFactor: Double
    )

}
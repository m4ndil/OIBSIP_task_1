package com.example.uc

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.uc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val converterData = listOf(
        Unit.Conversion("Feet to Meters", "Feet", "Meters", 0.3048),
        Unit.Conversion("Meters to Feet", "Meters", "Feet", 3.28084),

        Unit.Conversion("Inches to Centimeters", "Inches", "Centimeters", 2.54),
        Unit.Conversion("Centimeters to Inches", "Centimeters", "Inches", 0.393701),

        Unit.Conversion("Centimeters to Meters", "Centimeters", "Meters", 0.01),
        Unit.Conversion("Meters to Centimeters", "Meters", "Centimeters", 100.0),

        Unit.Conversion("Grams to Kilograms", "Grams", "Kilograms", 0.001),
        Unit.Conversion("Kilograms to Grams", "Kilograms", "Grams", 1000.0),
    )

    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        val unitNames = converterData.map { it.unitName }
        val a = ArrayAdapter(this, R.layout.simple_spinner_item, unitNames)
        a.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binder.unitsListSpinner.adapter = a

        binder.unitsListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle spinner item selection if needed
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        binder.convertBtn.setOnClickListener {
            val inputText = binder.inputET.text.toString().toDoubleOrNull()
            val selectedConversion = converterData[binder.unitsListSpinner.selectedItemPosition]

            if (inputText != null) {
                val result = inputText * selectedConversion.conversionFactor
                binder.resultTV.text = "${inputText} ${selectedConversion.fromUnit} = $result ${selectedConversion.toUnit}"
            } else {
                binder.resultTV.text = "Input a value."
            }
        }

        binder.resetBtn.setOnClickListener {
            binder.inputET.setText("0")
            binder.resultTV.text = "0"
        }
    }
}

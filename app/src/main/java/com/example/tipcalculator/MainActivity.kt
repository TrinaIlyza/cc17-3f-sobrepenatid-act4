package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode", "StringFormatInvalid", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val billAmountEditText: EditText = findViewById(R.id.billAmountEditText)
        val tipOptionsRadioGroup: RadioGroup = findViewById(R.id.tipOptionsRadioGroup)
        val roundUpSwitch: Switch = findViewById(R.id.roundUpSwitch)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val tipResultTextView: TextView = findViewById(R.id.tipResultTextView)

        // Set click listener for the calculate button
        calculateButton.setOnClickListener {
            // Get the bill amount entered by the user
            val billAmount = billAmountEditText.text.toString().toDoubleOrNull()
            if (billAmount == null || billAmount == 0.0) {
                tipResultTextView.text = ""
                return@setOnClickListener
            }

            // Get the selected tip percentage
            val tipPercentage = when (tipOptionsRadioGroup.checkedRadioButtonId) {
                R.id.amazingRadioButton -> 0.20
                R.id.goodRadioButton -> 0.18
                R.id.okRadioButton -> 0.15
                else -> 0.0
            }

            // Calculate the tip
            var tip = billAmount * tipPercentage

            // Check if the round up switch is on
            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            // Display the tip amount
            tipResultTextView.text = "Tip Amount: $%.2f".format(tip)
        }
    }
}
package com.example.a5in1mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DiskonActivity : AppCompatActivity() {

    private lateinit var originalPriceEditText: EditText
    private lateinit var discountPercentageEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diskon)

        originalPriceEditText = findViewById(R.id.originalPriceEditText)
        discountPercentageEditText = findViewById(R.id.discountPercentageEditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            calculateDiscountedPrice()
        }
    }

    private fun calculateDiscountedPrice() {
        val originalPrice = originalPriceEditText.text.toString().toDoubleOrNull()
        val discountPercentage = discountPercentageEditText.text.toString().toDoubleOrNull()

        if (originalPrice != null && discountPercentage != null) {
            val discountAmount = originalPrice * discountPercentage / 100
            val finalPrice = originalPrice - discountAmount
            resultTextView.text = "Final Price: $$finalPrice"
        } else {
            resultTextView.text = "Please enter valid values"
        }
    }
}

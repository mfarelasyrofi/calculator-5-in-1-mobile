package com.example.a5in1mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BmiActivity : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val weight = weightEditText.text.toString().toDoubleOrNull()
        val height = heightEditText.text.toString().toDoubleOrNull()

        if (weight != null && height != null) {
            val heightInMeters = height / 100
            val bmi = weight / (heightInMeters * heightInMeters)
            val bmiCategory = getBMICategory(bmi)
            resultTextView.text = "Indeks BMI Anda : %.2f\nCategory: $bmiCategory".format(bmi)
        } else {
            resultTextView.text = "Please enter valid values"
        }
    }

    private fun getBMICategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Kurus"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Gemuk"
            bmi >= 30.0 -> "Obesitas"
            else -> "Tidak diketahui"
        }
    }
}

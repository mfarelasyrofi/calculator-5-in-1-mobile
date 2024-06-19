package com.example.a5in1mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class KaloriActivity : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var activityLevelSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalori)

        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        ageEditText = findViewById(R.id.ageEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        activityLevelSpinner = findViewById(R.id.activityLevelSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Set up the spinner
        val activityLevels = arrayOf(
            "Sedentary (little or no exercise)",
            "Lightly active (light exercise/sports 1-3 days/week)",
            "Moderately active (moderate exercise/sports 3-5 days/week)",
            "Very active (hard exercise/sports 6-7 days a week)",
            "Super active (very hard exercise/sports & physical job)"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activityLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityLevelSpinner.adapter = adapter

        calculateButton.setOnClickListener {
            calculateCalories()
        }
    }

    private fun calculateCalories() {
        val weight = weightEditText.text.toString().toDoubleOrNull()
        val height = heightEditText.text.toString().toDoubleOrNull()
        val age = ageEditText.text.toString().toIntOrNull()
        val genderId = genderRadioGroup.checkedRadioButtonId
        val activityLevel = activityLevelSpinner.selectedItem.toString()

        if (weight != null && height != null && age != null && genderId != -1) {
            val isMale = genderId == R.id.maleRadioButton

            // Calculate BMR (Basal Metabolic Rate)
            val bmr = if (isMale) {
                88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age)
            } else {
                447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age)
            }

            // Calculate daily calorie needs based on activity level
            val calories = when (activityLevel) {
                "Sedentary (little or no exercise)" -> bmr * 1.2
                "Lightly active (light exercise/sports 1-3 days/week)" -> bmr * 1.375
                "Moderately active (moderate exercise/sports 3-5 days/week)" -> bmr * 1.55
                "Very active (hard exercise/sports 6-7 days a week)" -> bmr * 1.725
                "Super active (very hard exercise/sports & physical job)" -> bmr * 1.9
                else -> bmr * 1.2
            }

            resultTextView.text = "Your daily calorie needs: %.2f calories".format(calories)
        } else {
            resultTextView.text = "Please enter valid values"
        }
    }
}

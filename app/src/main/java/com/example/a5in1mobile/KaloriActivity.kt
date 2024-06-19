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
        val tingkatAktivitas = arrayOf(
            "Sedentari (sedikit atau tanpa olahraga)",
            "Aktif Ringan (olahraga ringan 1-3 hari/minggu)",
            "Aktif Sedang (olahraga sedang 3-5 hari/minggu)",
            "Sangat Aktif (olahraga berat 6-7 hari/minggu)",
            "Super Aktif (olahraga berat & pekerjaan fisik)"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tingkatAktivitas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityLevelSpinner.adapter = adapter

        calculateButton.setOnClickListener {
            hitungKalori()
        }
    }

    private fun hitungKalori() {
        val berat = weightEditText.text.toString().toDoubleOrNull()
        val tinggi = heightEditText.text.toString().toDoubleOrNull()
        val usia = ageEditText.text.toString().toIntOrNull()
        val jenisKelaminId = genderRadioGroup.checkedRadioButtonId
        val tingkatAktivitas = activityLevelSpinner.selectedItem.toString()

        if (berat != null && tinggi != null && usia != null && jenisKelaminId != -1) {
            val isPria = jenisKelaminId == R.id.maleRadioButton

            // Hitung BMR (Basal Metabolic Rate)
            val bmr = if (isPria) {
                88.362 + (13.397 * berat) + (4.799 * tinggi) - (5.677 * usia)
            } else {
                447.593 + (9.247 * berat) + (3.098 * tinggi) - (4.330 * usia)
            }

            // Hitung kebutuhan kalori harian berdasarkan tingkat aktivitas
            val kalori = when (tingkatAktivitas) {
                "Sedentari (sedikit atau tanpa olahraga)" -> bmr * 1.2
                "Aktif Ringan (olahraga ringan 1-3 hari/minggu)" -> bmr * 1.375
                "Aktif Sedang (olahraga sedang 3-5 hari/minggu)" -> bmr * 1.55
                "Sangat Aktif (olahraga berat 6-7 hari/minggu)" -> bmr * 1.725
                "Super Aktif (olahraga berat & pekerjaan fisik)" -> bmr * 1.9
                else -> bmr * 1.2
            }

            resultTextView.text = "Kebutuhan kalori harian Anda: %.2f kalori".format(kalori)
        } else {
            resultTextView.text = "Harap masukkan nilai yang valid"
        }
    }
}

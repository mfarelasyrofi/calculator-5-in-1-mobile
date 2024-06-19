package com.example.a5in1mobile

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PajakActivity : AppCompatActivity() {

    private lateinit var editTextIncome: EditText
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pajak)

        editTextIncome = findViewById(R.id.editTextIncome)
        textViewResult = findViewById(R.id.textViewResult)
    }

    fun calculateTax(view: android.view.View) {
        val incomeStr = editTextIncome.text.toString().trim()

        if (incomeStr.isEmpty()) {
            textViewResult.text = "Masukkan Total Pemasukan Anda!"
            return
        }

        val income = incomeStr.toDouble()

        val tax = calculateTaxAmount(income)

        val df = DecimalFormat("#,###.##")
        val formattedTax = df.format(tax)

        textViewResult.text = "Pajak Anda Sebesar: $formattedTax"
    }

    private fun calculateTaxAmount(income: Double): Double {
        val taxRate = 0.20
        return income * taxRate
    }
}

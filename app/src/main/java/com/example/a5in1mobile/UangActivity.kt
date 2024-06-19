package com.example.a5in1mobile

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class UangActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var fromCurrencySpinner: Spinner
    private lateinit var toCurrencySpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uang)

        amountEditText = findViewById(R.id.amountEditText)
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner)
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val currencies = listOf("USD", "EUR", "JPY", "IDR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        fromCurrencySpinner.adapter = adapter
        toCurrencySpinner.adapter = adapter

        convertButton.setOnClickListener {
            val amount = amountEditText.text.toString().toDouble()
            val fromCurrency = fromCurrencySpinner.selectedItem.toString()
            val toCurrency = toCurrencySpinner.selectedItem.toString()

            convertCurrency(amount, fromCurrency, toCurrency)
        }
    }

    private fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String) {
        val urlString = "https://api.exchangerate-api.com/v4/latest/$fromCurrency"

        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.doInput = true
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonResponse = JSONObject(response)
                val exchangeRate = jsonResponse.getJSONObject("rates").getDouble(toCurrency)
                val convertedAmount = amount * exchangeRate
                resultTextView.text = "$amount $fromCurrency = $convertedAmount $toCurrency"
            } else {
                resultTextView.text = "Failed to get exchange rates"
            }
        } catch (e: Exception) {
            resultTextView.text = "Error: ${e.message}"
        }
    }
}

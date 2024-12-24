package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Поля вводу
        val inputEta = findViewById<EditText>(R.id.inputEta)
        val inputCosPhi = findViewById<EditText>(R.id.inputCosPhi)
        val inputVoltage = findViewById<EditText>(R.id.inputVoltage)
        val inputCount = findViewById<EditText>(R.id.inputCount)
        val inputNominalPower = findViewById<EditText>(R.id.inputNominalPower)
        val inputUsageCoeff = findViewById<EditText>(R.id.inputUsageCoeff)
        val inputReactiveCoeff = findViewById<EditText>(R.id.inputReactiveCoeff)

        // Поля результатів
        val resultCurrent = findViewById<TextView>(R.id.resultCurrent)
        val resultActivePower = findViewById<TextView>(R.id.resultActivePower)
        val resultReactivePower = findViewById<TextView>(R.id.resultReactivePower)
        val resultFullPower = findViewById<TextView>(R.id.resultFullPower)

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val eta = inputEta.text.toString().toDouble()
            val cosPhi = inputCosPhi.text.toString().toDouble()
            val voltage = inputVoltage.text.toString().toDouble()
            val count = inputCount.text.toString().toInt()
            val nominalPower = inputNominalPower.text.toString().toDouble()
            val usageCoeff = inputUsageCoeff.text.toString().toDouble()
            val reactiveCoeff = inputReactiveCoeff.text.toString().toDouble()

            // Формули
            val totalPower = count * nominalPower * usageCoeff
            val reactivePower = totalPower * reactiveCoeff
            val activePower = totalPower * cosPhi
            val fullPower = sqrt(activePower * activePower + reactivePower * reactivePower)
            val current = fullPower / (sqrt(3.0) * voltage * eta)

            // Вивід результатів
            resultCurrent.text = "Розрахунковий струм: %.2f A".format(current)
            resultActivePower.text = "Активне навантаження: %.2f кВт".format(activePower)
            resultReactivePower.text = "Реактивне навантаження: %.2f квар".format(reactivePower)
            resultFullPower.text = "Повна потужність: %.2f кВА".format(fullPower)
        }
    }
}
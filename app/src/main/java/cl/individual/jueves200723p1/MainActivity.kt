package cl.individual.jueves200723p1

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import cl.individual.jueves200723p1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val divisas = listOf<String>("USD", "CLP", "EUR")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, divisas)

        binding.spnMonedaInicial.adapter = spinnerAdapter
        binding.spnMonedaConvertida.adapter = spinnerAdapter

        initListeners()
    }

    private fun initListeners() {
        binding.btnConvertir.setOnClickListener { v ->
            val montoInicial = binding.editMontoInicial.text.toString().toDouble()
            val divisaActual = binding.spnMonedaInicial.selectedItem.toString()
            val divisaCambio = binding.spnMonedaConvertida.selectedItem.toString()
            val resultado = convertirDivisas(montoInicial, divisaActual, divisaCambio)

            binding.txtResultadoConversion.text = "$divisaCambio $resultado"
        }

        binding.btnReset.setOnClickListener {
            limpiar()
        }


    }

    fun convertirDivisas(montoInicial: Double, divisaActual: String, divisaCambio: String): String {
        var resultado = montoInicial

        when (divisaActual) {
            "USD" -> if (divisaCambio == "CLP") {
                resultado = montoInicial * 817
            } else if (divisaCambio == "USD") {
                resultado = montoInicial * 1
            } else if (divisaCambio == "EUR") {
                resultado =  montoInicial * 0.89
            }

            "CLP" -> if (divisaCambio == "CLP") {
                resultado = montoInicial * 1
            } else if (divisaCambio == "USD") {
                resultado = montoInicial * 0.001
            } else if (divisaCambio == "EUR") {
                resultado = montoInicial * 0.01
            }

            "EUR" -> if (divisaCambio == "CLP") {
                resultado = montoInicial * 910
            } else if (divisaCambio == "USD") {
                resultado = montoInicial * 1.11
            } else if (divisaCambio == "EUR") {
                resultado = montoInicial * 1
            }
        }
        return resultado.toString()
    }

    fun limpiar() {
        binding.txtResultadoConversion.text = ""
        binding.editMontoInicial.setText("")
    }


}
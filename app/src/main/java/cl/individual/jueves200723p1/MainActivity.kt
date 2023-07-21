package cl.individual.jueves200723p1

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import cl.individual.jueves200723p1.databinding.ActivityMainBinding
import coil.load

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val divisas = listOf<String>("USD", "CLP", "EUR")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, divisas)

        initListeners()
        initSpinner(spinnerAdapter)
        cargarImagen()
    }

    private fun cargarImagen() {
        binding.imgLogo.load("https://images.unsplash.com/photo-1604594849809-dfedbc827105?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8bW9uZXl8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60")
    }

    private fun initSpinner(spinnerAdapter: SpinnerAdapter) {
        binding.spnMonedaInicial.adapter = spinnerAdapter
        binding.spnMonedaConvertida.adapter = spinnerAdapter
    }

    private fun initListeners() {
        binding.btnConvertir.setOnClickListener {
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
                resultado = montoInicial * 0.89
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
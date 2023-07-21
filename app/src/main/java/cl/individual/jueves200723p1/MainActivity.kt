package cl.individual.jueves200723p1

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import cl.individual.jueves200723p1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val divisas = listOf<String> ("Dolar", "Pesos", "Euro")
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
        binding.btnConvertir.setOnClickListener {v ->
            val montoInicial = binding.editMontoInicial.text.toString().toInt()
            val divisaActual = binding.spnMonedaInicial.selectedItem.toString()
            val divisaCambio = binding.spnMonedaConvertida.selectedItem.toString()


        }


    }
}
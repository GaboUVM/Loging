package com.example.loging

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PedidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        val nombreCliente = intent.getStringExtra("CLIENTE_NOMBRE") ?: "N/A"
        val telefonoCliente = intent.getStringExtra("CLIENTE_TELEFONO") ?: "N/A"
        val direccionCliente = intent.getStringExtra("CLIENTE_DIRECCION") ?: "N/A"

        val spEspecialidad = findViewById<Spinner>(R.id.spEspecialidad)
        val rgTamaño = findViewById<RadioGroup>(R.id.rgTamaño)
        val cbQueso = findViewById<CheckBox>(R.id.cbQueso)
        val cbPepperoni = findViewById<CheckBox>(R.id.cbPepperoni)
        val cbChampinones = findViewById<CheckBox>(R.id.cbChampinones)
        val cbRefresco = findViewById<CheckBox>(R.id.cbRefresco)
        val swEnvio = findViewById<Switch>(R.id.swEnvio)
        val spPago = findViewById<Spinner>(R.id.spPago)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        swEnvio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    this,
                    "El tiempo estimado de entrega es de 20 a 30 minutos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCalcular.setOnClickListener {
            var total = 0.0
            var resumen = "Cliente: $nombreCliente\n"
            resumen += "Teléfono: $telefonoCliente\n"
            resumen += "Dirección: $direccionCliente\n\n"
            resumen += "--- DESGLOSE DE LA ORDEN ---\n"

            val especialidad = spEspecialidad.selectedItem.toString()
            resumen += "Especialidad: $especialidad\n"

            when (rgTamaño.checkedRadioButtonId) {
                R.id.rbIndividual -> {
                    total += 100.0
                    resumen += "• Tamaño: Individual ($100 MXN)\n"
                }
                R.id.rbMediana -> {
                    total += 150.0
                    resumen += "• Tamaño: Mediana ($150 MXN)\n"
                }
                R.id.rbFamiliar -> {
                    total += 200.0
                    resumen += "• Tamaño: Familiar ($200 MXN)\n"
                }
            }

            if (cbQueso.isChecked) {
                total += 20.0
                resumen += "• Extra Queso ($20 MXN)\n"
            }
            if (cbPepperoni.isChecked) {
                total += 25.0
                resumen += "• Pepperoni ($25 MXN)\n"
            }
            if (cbChampinones.isChecked) {
                total += 15.0
                resumen += "• Champiñones ($15 MXN)\n"
            }
            if (cbRefresco.isChecked) {
                total += 35.0
                resumen += "• Refresco de 2L ($35 MXN)\n"
            }

            if (swEnvio.isChecked) {
                total += 30.0
                resumen += "• Envío Express ($30 MXN)\n"
            } else {
                resumen += "• Recoge en Sucursal ($0 MXN)\n"
            }

            val metodoPago = spPago.selectedItem.toString()
            resumen += "Forma de Pago: $metodoPago\n\n"
            resumen += "TOTAL A PAGAR: $$total MXN"

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ticket de Compra")
            builder.setMessage(resumen)
            builder.setPositiveButton("Aceptar", null)
            builder.show()
        }
    }
}<
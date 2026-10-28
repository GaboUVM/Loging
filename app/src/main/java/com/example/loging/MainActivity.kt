package com.example.loging

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val btnVerMenu = findViewById<Button>(R.id.btnVerMenu)
        val btnNuevoPedido = findViewById<Button>(R.id.btnNuevoPedido)

        btnVerMenu.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Nuestras Especialidades")
            builder.setMessage(
                "• Hawaiana: Jamón y Piña\n" +
                        "• Pepperoni Tradicional: Pepperoni y Doble Queso\n" +
                        "• Mexicana: Chorizo, Jalapeño y Cebolla\n" +
                        "• Suprema: Carnes Frías y Vegetales\n\n" +
                        "Precios base:\n" +
                        "Individual: $100 | Mediana: $150 | Familiar: $200"
            )
            builder.setPositiveButton("Entendido", null)
            builder.show()
        }

        btnNuevoPedido.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()

            if (nombre.isEmpty()) {
                etNombre.error = "Campo obligatorio"
                return@setOnClickListener
            }
            if (telefono.isEmpty()) {
                etTelefono.error = "Campo obligatorio"
                return@setOnClickListener
            }
            if (direccion.isEmpty()) {
                etDireccion.error = "Campo obligatorio"
                return@setOnClickListener
            }

            val intent = Intent(this, PedidoActivity::class.java).apply {
                putExtra("CLIENTE_NOMBRE", nombre)
                putExtra("CLIENTE_TELEFONO", telefono)
                putExtra("CLIENTE_DIRECCION", direccion)
            }
            startActivity(intent)
        }
    }
}
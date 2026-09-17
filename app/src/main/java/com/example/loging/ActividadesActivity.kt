package com.example.loging

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class ActividadesActivity : AppCompatActivity() {

    private lateinit var etActividad: EditText
    private lateinit var btnFecha: Button
    private lateinit var spPrioridad: Spinner
    private lateinit var btnAgregar: Button
    private lateinit var listaActividades: ListView
    private lateinit var btnRegresar: Button

    private val actividades = ArrayList<String>()
    private lateinit var adaptador: ArrayAdapter<String>

    private var fechaSeleccionada = "Sin fecha"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades)

        // Vincular elementos del diseño
        etActividad = findViewById(R.id.etActividad)
        btnFecha = findViewById(R.id.btnFecha)
        spPrioridad = findViewById(R.id.spPrioridad)
        btnAgregar = findViewById(R.id.btnAgregar)
        listaActividades = findViewById(R.id.listaActividades)
        btnRegresar = findViewById(R.id.btnRegresar)

        // Configurar las prioridades
        val prioridades = arrayOf("Baja", "Media", "Alta")

        val adaptadorPrioridades = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            prioridades
        )

        adaptadorPrioridades.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spPrioridad.adapter = adaptadorPrioridades

        // Configurar la lista de actividades
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            actividades
        )

        listaActividades.adapter = adaptador

        // Seleccionar fecha
        btnFecha.setOnClickListener {
            mostrarSelectorFecha()
        }

        // Agregar actividad
        btnAgregar.setOnClickListener {
            agregarActividad()
        }

        // Clic: marcar como completada
        listaActividades.setOnItemClickListener { _, _, posicion, _ ->

            val actividad = actividades[posicion]

            if (!actividad.startsWith("✓")) {
                actividades[posicion] = "✓ $actividad"
                adaptador.notifyDataSetChanged()

                Toast.makeText(
                    this,
                    "Actividad completada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Mantener presionado: eliminar
        listaActividades.setOnItemLongClickListener { _, _, posicion, _ ->

            confirmarEliminacion(posicion)

            true
        }

        // Regresar al menú
        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun mostrarSelectorFecha() {

        val calendario = Calendar.getInstance()

        val año = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val selector = DatePickerDialog(
            this,
            { _, añoSeleccionado, mesSeleccionado, diaSeleccionado ->

                fechaSeleccionada =
                    "$diaSeleccionado/${mesSeleccionado + 1}/$añoSeleccionado"

                btnFecha.text = "Fecha: $fechaSeleccionada"
            },
            año,
            mes,
            dia
        )

        selector.show()
    }

    private fun agregarActividad() {

        val nombre = etActividad.text.toString().trim()
        val prioridad = spPrioridad.selectedItem.toString()

        // Validar que exista un nombre
        if (nombre.isEmpty()) {

            etActividad.error = "Escribe el nombre de la actividad"

            Toast.makeText(
                this,
                "Debes escribir una actividad",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Crear la actividad
        val nuevaActividad =
            "$nombre | Prioridad: $prioridad | Fecha: $fechaSeleccionada"

        actividades.add(nuevaActividad)

        adaptador.notifyDataSetChanged()

        // Limpiar el campo
        etActividad.text.clear()

        Toast.makeText(
            this,
            "Actividad agregada",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun confirmarEliminacion(posicion: Int) {

        AlertDialog.Builder(this)
            .setTitle("Eliminar actividad")
            .setMessage("¿Deseas eliminar esta actividad?")
            .setPositiveButton("Eliminar") { _, _ ->

                actividades.removeAt(posicion)
                adaptador.notifyDataSetChanged()

                Toast.makeText(
                    this,
                    "Actividad eliminada",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
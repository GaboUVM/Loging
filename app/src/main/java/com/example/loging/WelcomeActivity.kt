package com.example.loging

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val tvWelcomeMessage = findViewById<TextView>(R.id.tvWelcomeMessage)
        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        val btnActividades = findViewById<Button>(R.id.btnActividades)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Recibir el usuario desde el Login
        val usuario = intent.getStringExtra("EXTRA_USUARIO")

        tvWelcomeMessage.text = "Bienvenido, $usuario"

        // Ir al perfil
        btnPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        // Ir a actividades
        btnActividades.setOnClickListener {
            val intent = Intent(this, ActividadesActivity::class.java)
            startActivity(intent)
        }

        // Cerrar sesión y regresar al Login
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
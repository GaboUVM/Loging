package com.example.loging

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val btnRegresarPerfil = findViewById<Button>(R.id.btnRegresarPerfil)

        btnRegresarPerfil.setOnClickListener {
            finish()
        }
    }
}
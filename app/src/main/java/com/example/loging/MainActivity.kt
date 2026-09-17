package com.example.loging

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val usuario = etUser.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (usuario.isEmpty()) {

                etUser.error = "El usuario no puede estar vacío"

                Toast.makeText(
                    this,
                    "Escribe tu usuario",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (password.isEmpty()) {

                etPassword.error = "La contraseña no puede estar vacía"

                Toast.makeText(
                    this,
                    "Escribe tu contraseña",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (usuario == "admin" && password == "1234") {

                Toast.makeText(
                    this,
                    "Inicio de sesión correcto",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("EXTRA_USUARIO", usuario)
                startActivity(intent)
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
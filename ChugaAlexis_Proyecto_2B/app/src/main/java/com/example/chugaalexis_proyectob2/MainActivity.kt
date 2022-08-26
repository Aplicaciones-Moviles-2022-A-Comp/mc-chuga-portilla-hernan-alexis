package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIniciar = findViewById<Button>(R.id.btn_iniciarSesion1)

        btnIniciar.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val btnSalir = findViewById<Button>(R.id.btn_salir)

        btnSalir.setOnClickListener{
            finish()
        }
    }

}
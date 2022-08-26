package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val crearCuenta = findViewById<TextView>(R.id.txt_crear_cuenta)
        crearCuenta.setOnClickListener {
            val intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }
    }

}
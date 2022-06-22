package com.example.movcomphacp2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Trabajo_Class02 : AppCompatActivity() {
    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trabajo_class02)

        val txtnum = findViewById<TextView>(R.id.text_numero)
        txtnum.text = "0"

        val btnAumentar = findViewById<Button>(R.id.btn_aumentar)
        btnAumentar.setOnClickListener{
            incrementarNum()
        }
    }

    fun incrementarNum() {
        num += 1
        val textViewContador = findViewById<TextView>(R.id.text_numero)
        textViewContador.text = num.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // GUARDAR LAS VARIABLES
            // SOLO PRIMITIVOS
            putInt("numeroGuardado", num)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado:Int? = savedInstanceState.getInt("numeroGuardado")
        if(numeroRecuperado != null){
            num = numeroRecuperado
            val txtContador = findViewById<TextView>(R.id.text_numero)
            txtContador.text = num.toString()
        }
    }
}
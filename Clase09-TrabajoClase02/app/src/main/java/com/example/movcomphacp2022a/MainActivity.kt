package com.example.movcomphacp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Alexis
        setContentView(R.layout.activity_main)
        Sentry.captureMessage("testing SDK setup", SentryLevel.INFO);
        val botonCicloVida=findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)

            }
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonTrabClass02=findViewById<Button>(R.id.btn_trabclass02)
        botonTrabClass02
            .setOnClickListener{
                irActividad(Trabajo_Class02::class.java)
            }
    }
    fun irActividad(
        clase:Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent)
    }
}
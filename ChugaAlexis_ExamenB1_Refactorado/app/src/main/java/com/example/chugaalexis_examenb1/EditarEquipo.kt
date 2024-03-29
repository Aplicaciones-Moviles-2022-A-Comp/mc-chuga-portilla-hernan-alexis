package com.example.chugaalexis_examenb1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarEquipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_equipo_editar)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionEquipo = intent.getIntExtra("posicionEditar",1)

        val editarNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreE_editar)
        val editarPrecio = findViewById<TextInputEditText>(R.id.txtIn_precioE_editar)
        val editarAnioF = findViewById<TextInputEditText>(R.id.txtIn_anioE_editar)
        val editarPerteneceFEF = findViewById<TextInputEditText>(R.id.txtIn_perteneceE_editar)
        val editarNumV = findViewById<TextInputEditText>(R.id.txtIn_numVE_editar)

        EquipoBaseDeDatos.TablaEquipo!!.listarEquipos().forEachIndexed{ indice: Int, equipo : EquipoFutbol ->

            if (indice == posicionEquipo){
                editarNombre.setText(equipo.nombreEquipo)
                editarPrecio.setText(equipo.precioEquipo.toString())
                editarAnioF.setText(equipo.anioFundacion.toString())
                editarPerteneceFEF.setText(equipo.perteneceFEF.toString())
                editarNumV.setText(equipo.numVictorias.toString())
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios)
        btnGuardarCambios.setOnClickListener {
            var nombreEquipo = editarNombre.text.toString()
            var precioEquipo = editarPrecio.text.toString().toDouble()
            var anioFundacion = editarAnioF.text.toString().toInt()
            var perteneceFEF = editarPerteneceFEF.text.toString()
            var numVictorias = editarNumV.text.toString().toInt()
            EquipoBaseDeDatos.TablaEquipo!!.actualizarEquipo(posicionEquipo,
                nombreEquipo,precioEquipo.toString(),anioFundacion.toString(),perteneceFEF,numVictorias.toString())

            val intentEditSucces = Intent(this, InicioEquipos::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InicioEquipos::class.java)
            startActivity(intentEditCancel)
        }

    }
}


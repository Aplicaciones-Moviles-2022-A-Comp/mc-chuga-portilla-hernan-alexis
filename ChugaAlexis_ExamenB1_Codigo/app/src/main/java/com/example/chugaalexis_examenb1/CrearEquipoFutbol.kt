package com.example.chugaalexis_examenb1



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearEquipoFutbol : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombreEquipo = ""
    var precioEquipo= 0.00
    var anioFundacion= 0
    var perteneceFEF= ""
    var numVictorias= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_crear)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
        var longitudListaEntreador = BBaseDeDatosMemoria.arregloEquipos.lastIndex
        BBaseDeDatosMemoria.arregloEquipos.forEachIndexed{ indice: Int, equipo : EquipoFutbol ->
            Log.i("testExamen","${equipo.idEquipo} -> ${equipo.nombreEquipo}")
            if (indice == longitudListaEntreador){
                lastId = equipo.idEquipo
            }
        }

        nextId = lastId+1

        var txtInNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreE_editar)
        var txtInPrecio = findViewById<TextInputEditText>(R.id.txtIn_precioE_editar)
        var txtInAnioF = findViewById<TextInputEditText>(R.id.txtIn_anioE_editar)
        var txtInPerteneceFEF = findViewById<TextInputEditText>(R.id.txtIn_perteneceE_editar)
        var txtInNumV = findViewById<TextInputEditText>(R.id.txtIn_numVE_editar)

        var btnCrearEquipo = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearEquipo.setOnClickListener {
            nombreEquipo = txtInNombre.text.toString()
            precioEquipo = txtInPrecio.text.toString().toDouble()
            anioFundacion= txtInAnioF.text.toString().toInt()
            perteneceFEF= txtInPerteneceFEF.text.toString()
            numVictorias= txtInNumV.text.toString().toInt()

            BBaseDeDatosMemoria.arregloEquipos.add(
                EquipoFutbol(nextId, nombreEquipo, precioEquipo,anioFundacion,perteneceFEF,numVictorias)
            )
            val intentAddSucces = Intent(this, InicioEquipos::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarEquipos = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarEquipos.setOnClickListener {
            val intentAddCancel = Intent(this, InicioEquipos::class.java)
            startActivity(intentAddCancel)
        }
    }

}
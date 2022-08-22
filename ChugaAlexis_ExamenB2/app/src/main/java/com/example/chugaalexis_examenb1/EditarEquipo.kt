package com.example.chugaalexis_examenb1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarEquipo : AppCompatActivity() {
    var equipoSeleccionado = EquipoFutbol("", "", 0.0, 0, "", 0)
    val db = Firebase.firestore
    val equipos = db.collection("Equipos_Futbol")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_equipo_editar)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        equipoSeleccionado = intent.getParcelableExtra<EquipoFutbol>("PosEquipoFultbol")!!

        val editarNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreE_editar)
        val editarPrecio = findViewById<TextInputEditText>(R.id.txtIn_precioE_editar)
        val editarAnioF = findViewById<TextInputEditText>(R.id.txtIn_anioE_editar)
        val editarPerteneceFEF = findViewById<TextInputEditText>(R.id.txtIn_perteneceE_editar)
        val editarNumV = findViewById<TextInputEditText>(R.id.txtIn_numVE_editar)

        editarNombre.setText(equipoSeleccionado.nombreEquipo)
        editarPrecio.setText(equipoSeleccionado.precioEquipo.toString())
        editarAnioF.setText(equipoSeleccionado.anioFundacion.toString())
        editarPerteneceFEF.setText(equipoSeleccionado.perteneceFEF.toString())
        editarNumV.setText(equipoSeleccionado.numVictorias.toString())



        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios)
        btnGuardarCambios.setOnClickListener {
            equipos.document("${equipoSeleccionado.idEquipo}").update(
                "nombreEquipo", editarNombre.text.toString(),
                "precioEquipo",editarPrecio.text.toString(),
                "anioFundacion",editarAnioF.text.toString(),
                "perteneceFEF",editarPerteneceFEF.text.toString(),
                "numVictorias",editarNumV.text.toString()
            )
            Toast.makeText(this,"Equipo actualizado exitosamente", Toast.LENGTH_SHORT).show()

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


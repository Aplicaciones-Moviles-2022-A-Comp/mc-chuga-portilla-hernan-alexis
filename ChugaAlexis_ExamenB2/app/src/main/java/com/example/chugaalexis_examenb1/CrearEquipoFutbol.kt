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

class CrearEquipoFutbol : AppCompatActivity() {

    val db = Firebase.firestore
    val equipos = db.collection("Equipos_Futbol")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_crear)
    }

    override fun onStart() {
        super.onStart()

        var txtInNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreE_editar)
        var txtInPrecio = findViewById<TextInputEditText>(R.id.txtIn_precioE_editar)
        var txtInAnioF = findViewById<TextInputEditText>(R.id.txtIn_anioE_editar)
        var txtInPerteneceFEF = findViewById<TextInputEditText>(R.id.txtIn_perteneceE_editar)
        var txtInNumV = findViewById<TextInputEditText>(R.id.txtIn_numVE_editar)

        var btnCrearEquipo = findViewById<Button>(R.id.btn_crear_equipo)
        btnCrearEquipo.setOnClickListener {
            var equipo= hashMapOf(
            "nombreEquipo" to txtInNombre.text.toString(),
            "precioEquipo" to txtInPrecio.text.toString(),
            "anioFundacion" to txtInAnioF.text.toString(),
            "perteneceFEF" to txtInPerteneceFEF.text.toString(),
            "numVictorias" to txtInNumV.text.toString())
            equipos.add(equipo).addOnSuccessListener {
                txtInNombre.text!!.clear()
                txtInAnioF.text!!.clear()
                txtInPerteneceFEF.text!!.clear()
                txtInPrecio.text!!.clear()
                txtInNumV.text!!.clear()
                Toast.makeText(this,"Equipo registrado con exito", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Equipo","Success")
            }.addOnSuccessListener {
                Log.i("Crear-Equipo","Failed")
            }


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
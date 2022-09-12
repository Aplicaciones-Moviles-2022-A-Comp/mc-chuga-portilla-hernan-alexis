package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearTarea : AppCompatActivity() {
    val db = Firebase.firestore
    val categorias = db.collection("Categorias_Proyecto")
    var categoriaSeleccionada=Categoria("","","")
     var tareaSeleccionada=Tarea("","","","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tarea)
    }

    override fun onStart() {
        super.onStart()
        categoriaSeleccionada=intent.getParcelableExtra<Categoria>("posicionCat")!!
        val catSubColeccion = categorias.document("${categoriaSeleccionada.idCategoria}")
            .collection("Tareas")
        var txtTitulo= findViewById<EditText>(R.id.txt_titulo_tarea)
        var txtFecha= findViewById<EditText>(R.id.txt_fecha)
        var txtDescripcion= findViewById<EditText>(R.id.txt_decripcion_tarea)



        var btnCrearTarea= findViewById<Button>(R.id.btn_crearTarea)
        btnCrearTarea.setOnClickListener {
            var tarea=hashMapOf(
                "Titulo" to txtTitulo.text.toString(),
                "Fecha Limite" to txtFecha.text.toString(),
                "Descripcion" to txtDescripcion.text.toString(),
                "IDCategoria" to categoriaSeleccionada.idCategoria,)
            catSubColeccion.add(tarea).addOnSuccessListener{
                Log.i("Crear-Tarea", "Con exito")
            }.addOnFailureListener {
                Log.i("Crear-Tarea", "Fallido")
            }

            val intentAddSucces = Intent(this, InicioTareas::class.java)
            intentAddSucces.putExtra("PosCategoria",categoriaSeleccionada)
            startActivity(intentAddSucces)

            var btnAtrasCrearTarea= findViewById<Button>(R.id.txt_atras_crear_tarea)
            btnAtrasCrearTarea.setOnClickListener {
                val intentAddSucces = Intent(this, InicioTareas::class.java)
                startActivity(intentAddSucces)
            }
        }

    }}


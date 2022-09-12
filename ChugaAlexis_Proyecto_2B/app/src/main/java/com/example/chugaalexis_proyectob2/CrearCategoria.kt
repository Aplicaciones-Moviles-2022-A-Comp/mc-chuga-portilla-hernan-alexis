package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCategoria : AppCompatActivity() {
    val db = Firebase.firestore
    val categorias = db.collection("Categorias_Proyecto")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_categoria)
    }

    override fun onStart() {
        super.onStart()
        var txtnombreCategoria= findViewById<EditText>(R.id.txt_nombreCategoria)
        var txtDescripcion= findViewById<EditText>(R.id.txt_descripcion_categoria)


        var btnCrearCuategoria= findViewById<Button>(R.id.btn_crear_categoria)
        btnCrearCuategoria.setOnClickListener{
            var categoria=hashMapOf(
                "Nombre" to txtnombreCategoria.text.toString(),
                "Descripcion" to txtDescripcion.text.toString())

                categorias.add(categoria).addOnSuccessListener{
                    txtnombreCategoria.text!!.clear()
                    txtDescripcion.text!!.clear()
                }.addOnSuccessListener {
                    Log.i("Crear-Cat","Failed")
                }

                val intentAddSucces = Intent(this, InicioCategorias::class.java)
                startActivity(intentAddSucces)
        }
        var atrasCategoria= findViewById<Button>(R.id.btn_atras_crear_categorias)
        atrasCategoria.setOnClickListener{
            val intentAddCancel = Intent(this, InicioTareas::class.java)
            startActivity(intentAddCancel)
        }
    }
}
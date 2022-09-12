package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarCategoria : AppCompatActivity() {
    var catSeleccionado = Categoria("","","")
    val db = Firebase.firestore
    val categorias = db.collection("Categorias_proyecto")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_categoria)
    }
    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()
        var editarnombreCategoria= findViewById<EditText>(R.id.txt_titCat_editar)
        var editarDescripcion= findViewById<EditText>(R.id.txt_descCat_editar)

        catSeleccionado = intent.getParcelableExtra<Categoria>("PosCategoria")!!
        editarnombreCategoria.setText(catSeleccionado.idCategoria)
        editarDescripcion.setText(catSeleccionado.descripcion)

        val btnGuardarCambiosCat = findViewById<Button>(R.id.btn_editar_Categoria)
        btnGuardarCambiosCat.setOnClickListener {
            categorias.document("${catSeleccionado.idCategoria}").update(
                "Nombre",editarnombreCategoria.text.toString(),
                "Descripcion",editarDescripcion.text.toString()
            )
            val intentEditSucces = Intent(this, InicioCategorias::class.java)
            startActivity(intentEditSucces)
        }
}}
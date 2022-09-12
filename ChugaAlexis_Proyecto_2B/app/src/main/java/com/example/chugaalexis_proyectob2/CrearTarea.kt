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
    var adaptadorSpinner: ArrayAdapter<Categoria>?=null
    val categorias = db.collection("Categorias_Proyecto")
    var categoriaSeleccionada=Categoria("","","")
     var tareaSeleccionada=Tarea("","","","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tarea)
    }

    override fun onStart() {
        super.onStart()


        var txtTitulo= findViewById<EditText>(R.id.txt_titulo_tarea)
        var txtFecha= findViewById<EditText>(R.id.txt_fecha)
        var txtDescripcion= findViewById<EditText>(R.id.txt_decripcion_tarea)
        var categoriaT= findViewById<Spinner>(R.id.spinner_categoria_crearTarea)
        categorias.get().addOnSuccessListener { result ->
            var listaCategoria = arrayListOf<Categoria>()
            for(document in result){
                listaCategoria.add(
                    Categoria(
                        document.id.toString(),
                        document.data.get("Nombre").toString(),
                        document.data.get("Descripcion").toString(),
                    )
                )}
            adaptadorSpinner = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listaCategoria
            )
            categoriaT.adapter=adaptadorSpinner
            adaptadorSpinner!!.notifyDataSetChanged()

            categoriaT.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    categoriaSeleccionada= categoriaT.selectedItem as Categoria

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }}}

        val catSubColeccion = categorias.document("${categoriaSeleccionada.idCategoria}")
            .collection("Tareas")

        var btnCrearTarea= findViewById<Button>(R.id.btn_crearTarea)
        btnCrearTarea.setOnClickListener {
            var tarea=hashMapOf(
                "Titulo" to txtTitulo.text.toString(),
                "Fecha Limite" to txtFecha.text.toString(),
                "Descripcion" to txtDescripcion.text.toString(),
                "IDCategoria" to categoriaSeleccionada.idCategoria,)
            catSubColeccion.add(tarea).addOnSuccessListener{
                txtTitulo.text!!.clear()
                txtFecha.text!!.clear()
                txtDescripcion.text!!.clear()
            }

            val intentAddSucces = Intent(this, InicioTareas::class.java)
            startActivity(intentAddSucces)

            var btnAtrasCrearTarea= findViewById<Button>(R.id.txt_atras_crear_tarea)
            btnAtrasCrearTarea.setOnClickListener {
                val intentAddSucces = Intent(this, InicioTareas::class.java)
                startActivity(intentAddSucces)
            }
        }

    }}


package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioCategorias : AppCompatActivity() {
    val db = Firebase.firestore
    val equipos = db.collection("Categorias_Proyecto")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Categoria>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_categorias)
    }
    override fun onStart() {
        super.onStart()
        listarCategorias()
        val btnAnadirCat = findViewById<Button>(R.id.btn_agregar_categoria)
        btnAnadirCat.setOnClickListener {
            val intentAddEquipo = Intent(this, CrearCategoria::class.java)
            startActivity(intentAddEquipo)
        }
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_categorias, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var categoriaSeleccionado:Categoria = adaptador!!.getItem(idItemSeleccionado)!!

        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${categoriaSeleccionado.idCategoria}")
                val abrirEditarCategoria = Intent(this, EditarCategoria::class.java)
                abrirEditarCategoria.putExtra("PosCategoria",categoriaSeleccionado)
                startActivity(abrirEditarCategoria)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delte position: ${idItemSeleccionado}")
                equipos.document("${categoriaSeleccionado.idCategoria}").delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Categoria","Exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Categoria","Fallido")
                    }

                listarCategorias()
                return true
            }

            R.id.mi_tareas -> {
                Log.i("context-menu", "Jugadores: ${idItemSeleccionado}")
                val abrirInicioTareas = Intent(this, InicioTareas::class.java)
                abrirInicioTareas.putExtra("PosCategoria",categoriaSeleccionado)
                startActivity(abrirInicioTareas)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun listarCategorias(){
        val lv_categorias = findViewById<ListView>(R.id.lv_lista_categorias)
        equipos.get().addOnSuccessListener{ result ->
            var catLista = arrayListOf<Categoria>()
            for (document in result) {
                catLista.add(
                    Categoria(
                        document.id.toString(),
                        document.get("Nombre").toString(),
                        document.get("Descripcion").toString()
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                catLista
            )
            lv_categorias.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_categorias)

        }.addOnFailureListener{
            Log.i("Error", "Creacion de lista de equipos fallida")
        }
    }
}
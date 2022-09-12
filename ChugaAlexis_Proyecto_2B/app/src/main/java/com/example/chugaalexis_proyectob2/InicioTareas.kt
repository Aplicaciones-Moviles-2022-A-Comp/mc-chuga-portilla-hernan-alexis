package com.example.chugaalexis_proyectob2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioTareas : AppCompatActivity() {
    var categoriaSeleccionada=Categoria("","","")
    val db = Firebase.firestore
    val categorias = db.collection("Categorias_Proyecto")
    var adaptador: ArrayAdapter<Tarea>?=null
    var idItemSeleccionado = 0
    var tareaSeleccionada= Tarea("","","","","")

    var resultAnadirJugador = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                categoriaSeleccionada = intent.getParcelableExtra<Categoria>("PosCategoria")!!
            }
        }

    }
    var resultEditarJugador = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                categoriaSeleccionada = intent.getParcelableExtra<Categoria>("PosCategoria")!!
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_tareas)
    }

    override fun onStart() {
        categoriaSeleccionada = intent.getParcelableExtra<Categoria>("PosCategoria")!!
        super.onStart()
        var txtCat = findViewById<TextView>(R.id.tv_categoria_inicio_tareas)
        txtCat.setText("Categoria: "+categoriaSeleccionada.nombre)
        categoriaSeleccionada=intent.getParcelableExtra<Categoria>("PosCategoria")!!
        listViewTareas()
        var btn_crearTarea=findViewById<Button>(R.id.btn_agregar_tarea)
        btn_crearTarea.setOnClickListener{
            abrirActividadAddTarea(CrearTarea::class.java)
        }

    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menutareas, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSeleccionado = info.position
        Log.i("context-menu", "ID Jugador ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        tareaSeleccionada = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarTarea(EditarTareas::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val catSubColeccion= categorias.document("${categoriaSeleccionada.idCategoria}")
                    .collection("Tareas")
                    .document("${tareaSeleccionada!!.idTarea}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-tarea","Con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-tarea","Fallido")
                    }
                listViewTareas()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun listViewTareas(){
        val catSubColeccion= categorias.document("${categoriaSeleccionada.idCategoria}")
            .collection("Tareas")
            .whereEqualTo("IDCategoria","${categoriaSeleccionada.idCategoria}")
        val tareas_lv = findViewById<ListView>(R.id.lv_lista_tareas)
        catSubColeccion.get().addOnSuccessListener { result ->
            var listaTareas = arrayListOf<Tarea>()
            for(document in result){
                listaTareas.add(
                    Tarea(
                        document.id.toString(),
                        document.data.get("Titulo").toString(),
                        document.data.get("Fecha Limite").toString(),
                        document.data.get("Descripcion").toString(),
                        document.data.get("IDCategoria").toString()
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaTareas
            )
            tareas_lv.adapter=adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(tareas_lv)
        }
    }
    fun abrirActividadEditarTarea(
        clase: Class<*>
    ) {
        val intentEditarJugador = Intent(this, clase)
        intentEditarJugador.putExtra("tarea", tareaSeleccionada)
        intentEditarJugador.putExtra("posicionCateditar",categoriaSeleccionada)
        resultEditarJugador.launch(intentEditarJugador)
    }

    fun abrirActividadAddTarea(
        clase: Class<*>
    ) {
        val intentAddNewJugador = Intent(this, clase)
        intentAddNewJugador.putExtra("posicionCat",categoriaSeleccionada)
        resultAnadirJugador.launch(intentAddNewJugador)
    }
}
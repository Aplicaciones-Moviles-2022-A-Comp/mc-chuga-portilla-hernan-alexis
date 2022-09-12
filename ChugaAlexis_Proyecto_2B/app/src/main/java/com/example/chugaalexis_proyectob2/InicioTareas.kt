package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioTareas : AppCompatActivity() {
    var categoriaSeleccionada=Categoria("","","")
    val db = Firebase.firestore
    val categorias = db.collection("Categorias_Proyecto")
    var adaptador: ArrayAdapter<Tarea>?=null
    var adaptadorSpinner:ArrayAdapter<Categoria>?=null
    var idItemSeleccionado = 0
    var tareaSeleccionada= Tarea("","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_tareas)
    }

    override fun onStart() {
        super.onStart()
        var spinnerCat= findViewById<Spinner>(R.id.spinner_cat_inicioT)

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
        spinnerCat.adapter=adaptadorSpinner
        adaptadorSpinner!!.notifyDataSetChanged()
        spinnerCat.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               categoriaSeleccionada= spinnerCat.selectedItem as Categoria
                listarTareas()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        }
        var btn_crearCuenta=findViewById<Button>(R.id.btn_agregar_tarea)
        btn_crearCuenta.setOnClickListener{
            val intent = Intent(this, CrearTarea::class.java)
            startActivity(intent)
        }
        var btn_crearCategoria=findViewById<Button>(R.id.btn_cCat_inicio)
        btn_crearCategoria.setOnClickListener{
            val intent = Intent(this, CrearCategoria::class.java)
            startActivity(intent)
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
    /*
    override fun onContextItemSelected(item: MenuItem): Boolean {
        jugadorSeleccionado = adaptador!!.getItem(idItemSeleccionado)
        return when (item.itemId) {
            R.id.mi_editarJugador -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarJugador(EditarJugador::class.java)
                return true
            }
            R.id.mi_eliminarJugador -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val equipoSubColeccion= equipos.document("${equipoSeleccionado.idEquipo}")
                    .collection("Jugadores_Futbol")
                    .document("${jugadorSeleccionado!!.idJugador}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Jugador","Con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Jugador","Fallido")
                    }
                listViewJugadores()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }*/
    fun listarTareas(){
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
                        document.data.get("titulo").toString(),
                        document.data.get("descripcion").toString(),
                        document.data.get("fechaLimite").toString(),
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
}
package com.example.chugaalexis_examenb1


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

class InicioEquipos : AppCompatActivity() {
    val db = Firebase.firestore
    val equipos = db.collection("Equipos_Futbol")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<EquipoFutbol>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_equipo)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        listarEquipos()
        val btnAnadirEquipo = findViewById<Button>(R.id.btn_crear_nuevo_equipo)
        btnAnadirEquipo.setOnClickListener {
            val intentAddEquipo = Intent(this, CrearEquipoFutbol::class.java)
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var equipoSeleccionado:EquipoFutbol = adaptador!!.getItem(idItemSeleccionado)!!

        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${equipoSeleccionado.idEquipo}")
                val abrirEditarEquipo = Intent(this, EditarEquipo::class.java)
                abrirEditarEquipo.putExtra("PosEquipoFultbol",equipoSeleccionado)
                startActivity(abrirEditarEquipo)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delte position: ${idItemSeleccionado}")
                equipos.document("${equipoSeleccionado.idEquipo}").delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Equipo","Exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Equipo","Fallido")
                    }

                listarEquipos()
                return true
            }
            R.id.mi_jugadores -> {
                Log.i("context-menu", "Jugadores: ${idItemSeleccionado}")
                val abrirInicioJugadores = Intent(this, InicioJugadores::class.java)
                abrirInicioJugadores.putExtra("PosEquipoFutbol",equipoSeleccionado)
                startActivity(abrirInicioJugadores)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun listarEquipos(){
        val lv_equipos = findViewById<ListView>(R.id.lv_equipos_lista)
        equipos.get().addOnSuccessListener{ result ->
            var equiposLista = arrayListOf<EquipoFutbol>()
            for (document in result) {
                equiposLista.add(
                    EquipoFutbol(
                        document.id.toString(),
                        document.get("nombreEquipo").toString(),
                        document.get("precioEquipo").toString().toDouble(),
                        document.get("anioFundacion").toString().toInt(),
                        document.get("perteneceFEF").toString(),
                        document.get("numVictorias").toString().toInt(),
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                equiposLista
            )
            lv_equipos.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_equipos)

        }.addOnFailureListener{
            Log.i("Error", "Creacion de lista de equipos fallida")
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarEquipo = Intent(this, clase)
        intentEditarEquipo.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarEquipo)
    }


}
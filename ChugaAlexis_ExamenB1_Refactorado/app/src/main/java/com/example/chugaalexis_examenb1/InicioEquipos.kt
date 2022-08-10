package com.example.chugaalexis_examenb1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class InicioEquipos : AppCompatActivity() {

    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_equipo)
        Log.i("ciclo-vida", "onCreate")
       // EBaseDeDatos.TablaEquipo!!.crearEquipo("BSC","2500","2000","si","100")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewEquipo = findViewById<ListView>(R.id.lv_equipos_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EquipoBaseDeDatos.TablaEquipo!!.listarEquipos()
        )
        listViewEquipo.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewEquipo)

        val btnAnadirEquipo = findViewById<Button>(R.id.btn_crear_nuevo_equipo)
        btnAnadirEquipo.setOnClickListener {
            val intentAddEquipo = Intent(this, CrearEquipoFutbol::class.java)
            startActivity(intentAddEquipo)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Guardar las variables
            // primitivos
            putInt("idItemSeleccionado",idItemSeleccionado)
            putParcelableArrayList("arregloEquipo",EquipoBaseDeDatos.TablaEquipo!!.listarEquipos())
            putParcelableArrayList("arregloE-J",Registros.arregloEquipos_Jugadores)

        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        idItemSeleccionado = savedInstanceState.getInt("idItemSeleccionado")

        Registros.arregloEquipos_Jugadores = savedInstanceState.getParcelableArrayList<Equipo_Jugador>("arregloE-J")!!
        if (idItemSeleccionado == null){
            idItemSeleccionado = 0
        }
        val listViewEquipo = findViewById<ListView>(R.id.lv_equipos_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EquipoBaseDeDatos.TablaEquipo!!.listarEquipos()
        )
        listViewEquipo.adapter = adaptador
        adaptador.notifyDataSetChanged()
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
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadConParametros(EditarEquipo::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                EquipoBaseDeDatos.TablaEquipo!!.eliminarEquipos(idItemSeleccionado)
                val listViewEquipo = findViewById<ListView>(R.id.lv_equipos_lista)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    EquipoBaseDeDatos.TablaEquipo!!.listarEquipos()
                )
                listViewEquipo.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
            R.id.mi_jugadores -> {
                Log.i("context-menu", "Jugadores: ${idItemSeleccionado}")
                abrirActividadConParametros(InicioJugadores::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
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
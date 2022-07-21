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

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewEntrenador = findViewById<ListView>(R.id.lv_equipos_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloEquipos
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewEntrenador)

        val btnAnadirEntrenador = findViewById<Button>(R.id.btn_crear_nuevo_equipo)
        btnAnadirEntrenador.setOnClickListener {
            val intentAddEntrenador = Intent(this, CrearEquipoFutbol::class.java)
            startActivity(intentAddEntrenador)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Guardar las variables
            // primitivos
            putInt("idItemSeleccionado",idItemSeleccionado)
            putParcelableArrayList("arregloEntrenador",BBaseDeDatosMemoria.arregloEquipos)
            putParcelableArrayList("arregloEntrenadorXpokemon",BBaseDeDatosMemoria.arregloEquipos_Jugadores)
            putParcelableArrayList("arregloPokemon",BBaseDeDatosMemoria.arregloJugadores)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        idItemSeleccionado = savedInstanceState.getInt("idItemSeleccionado")
        BBaseDeDatosMemoria.arregloEquipos = savedInstanceState.getParcelableArrayList<EquipoFutbol>("arregloEntrenador")!!
        BBaseDeDatosMemoria.arregloEquipos_Jugadores = savedInstanceState.getParcelableArrayList<Equipo_Jugador>("arregloEntrenadorXpokemon")!!
        BBaseDeDatosMemoria.arregloJugadores = savedInstanceState.getParcelableArrayList<JugadorFutbol>("arregloPokemon")!!
        if (idItemSeleccionado == null){
            idItemSeleccionado = 0
        }
        val listViewEntrenador = findViewById<ListView>(R.id.lv_equipos_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloEquipos
        )
        listViewEntrenador.adapter = adaptador
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
                eliminarEntrenador(idItemSeleccionado)
                return true
            }
            R.id.mi_jugadores -> {
                Log.i("context-menu", "Pokemons: ${idItemSeleccionado}")
                abrirActividadConParametros(InicioJugadores::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarEntrenador = Intent(this, clase)
        intentEditarEntrenador.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarEntrenador)
    }

    fun eliminarEntrenador(
        posicioEntrenadorEnliminar: Int
    ) {
        val listViewEntrenador = findViewById<ListView>(R.id.lv_equipos_lista)

        var entrenadorAeliminar = BBaseDeDatosMemoria.arregloEquipos.elementAt(posicioEntrenadorEnliminar)
        var idEntrenadorAeliminar = entrenadorAeliminar.idEquipo

        var auxListaEntrenadorXpokemon = arrayListOf<Equipo_Jugador>()

        BBaseDeDatosMemoria.arregloEquipos_Jugadores.forEachIndexed{ indice: Int, equipo_jugador: Equipo_Jugador ->
            if(idEntrenadorAeliminar != equipo_jugador.idEquipo){
                auxListaEntrenadorXpokemon.add(equipo_jugador)
            }
        }

        BBaseDeDatosMemoria.arregloEquipos.removeAt(posicioEntrenadorEnliminar)
        BBaseDeDatosMemoria.arregloEquipos_Jugadores = auxListaEntrenadorXpokemon

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloEquipos
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}
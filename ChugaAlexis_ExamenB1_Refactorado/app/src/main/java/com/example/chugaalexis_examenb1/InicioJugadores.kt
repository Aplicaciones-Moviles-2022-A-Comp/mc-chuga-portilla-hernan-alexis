package com.example.chugaalexis_examenb1

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

class InicioJugadores : AppCompatActivity() {

    var idItemSeleccionado = 0
    var equipoPos = 0
    var itemS = 0
    var idEquipo=0


    var resultAnadirJugador = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                equipoPos = data?.getIntExtra("posicionEquipo",0)!!
            }
        }

    }

    var resultEditarJugador = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                equipoPos = data?.getIntExtra("posicionEquipo",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_jugador)
    }
    fun listViewJugadores():ArrayList<JugadorFutbol> {
        var listaIDJugadores = arrayListOf<Int>()

        Registros.arregloEquipos_Jugadores.forEachIndexed { indice: Int, equipoJugador: Equipo_Jugador ->
            if (equipoJugador.idEquipo == idEquipo) {
                listaIDJugadores.add(equipoJugador.idJugador)
            }
        }
        var listaJugadores = arrayListOf<JugadorFutbol>()
        EquipoBaseDeDatos.TablaEquipo!!.listarJugadores()
            .forEachIndexed { indice: Int, jugador: JugadorFutbol ->
                for (i in listaIDJugadores) {
                    if (i == jugador.idJugador) {
                        listaJugadores.add(jugador)
                    }
                }
            }
        return listaJugadores
    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        equipoPos = intent.getIntExtra("posicionEditar",1)
        EquipoBaseDeDatos.TablaEquipo!!.listarEquipos().forEachIndexed { indice: Int, equipo: EquipoFutbol ->
            if (indice == equipoPos) {
               val txtNombreEquipo=findViewById<TextView>(R.id.tv_nombreE_J)
                txtNombreEquipo.setText("Equipo: "+equipo.nombreEquipo)
                idEquipo=equipo.idEquipo
            }

            }

        val listViewJugador = findViewById<ListView>(R.id.lv_jugadores_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewJugadores()
        )
        listViewJugador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearJugador = findViewById<Button>(R.id.btn_crear_jugador)
        btnCrearJugador.setOnClickListener {
            abrirActividadAddJugador(CrearJugadorFutbol::class.java)
        }

        val btnVolverJugador = findViewById<Button>(R.id.btn_volver_jugador)
        btnVolverJugador.setOnClickListener {
            val intentAtrasJugador = Intent(this, InicioEquipos::class.java)
            startActivity(intentAtrasJugador)
        }


        this.registerForContextMenu(listViewJugador)

    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val listViewJugador = findViewById<ListView>(R.id.lv_jugadores_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewJugadores()
        )
        listViewJugador.adapter = adaptador
        adaptador.notifyDataSetChanged()

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_jugador, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemS = id
        val idR=listViewJugadores()[id].idJugador
        idItemSeleccionado = idR//idE_J.elementAt(id)
        Log.i("context-menu", "ID Jugador ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarJugador -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarJugador(EditarJugador::class.java)
                return true
            }
            R.id.mi_eliminarJugador -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                EquipoBaseDeDatos.TablaEquipo!!.eliminarJugadores(idItemSeleccionado)
                val listViewJugador = findViewById<ListView>(R.id.lv_jugadores_lista)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listViewJugadores()
                )
                listViewJugador.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarJugador(
        clase: Class<*>
    ) {
        val intentEditarJugador = Intent(this, clase)
        intentEditarJugador.putExtra("jugador", idItemSeleccionado)
        intentEditarJugador.putExtra("posicionEquipoeditar",equipoPos)
        resultEditarJugador.launch(intentEditarJugador)
    }

    fun abrirActividadAddJugador(
        clase: Class<*>
    ) {
        val intentAddNewJugador = Intent(this, clase)
        intentAddNewJugador.putExtra("posicionEquipo",equipoPos)
        Log.i("positionSend","${equipoPos}")
        resultAnadirJugador.launch(intentAddNewJugador)
    }


}
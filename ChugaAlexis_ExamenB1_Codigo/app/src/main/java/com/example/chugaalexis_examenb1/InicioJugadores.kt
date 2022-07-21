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
    var idEquipoS = 0
    var equipoPos = 0
    var itemS = 0

    var listaJugadores = arrayListOf<String>()
    var idE_J = arrayListOf<Int>()

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

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        listaJugadores = arrayListOf()
        idE_J = arrayListOf()


        equipoPos = intent.getIntExtra("posicionEditar",1)



        val nombreEquipo_jugador = findViewById<TextView>(R.id.tv_nombreE_J)

        BBaseDeDatosMemoria.arregloEquipos.forEachIndexed{ indice: Int, equipo : EquipoFutbol ->
            if (indice == equipoPos){
                idEquipoS = equipo.idEquipo
                var label = "Equipo: ${equipo.nombreEquipo}"
                nombreEquipo_jugador.setText(label)
            }
        }

        BBaseDeDatosMemoria.arregloEquipos_Jugadores.forEachIndexed{ indice: Int, equipo_jugador : Equipo_Jugador ->
            if (idEquipoS == equipo_jugador.idEquipo){
                listaJugadores.add(equipo_jugador.nombreE_J.toString())
                idE_J.add(equipo_jugador.idEquipo_Jugador)
            }
        }

        val listViewJugador = findViewById<ListView>(R.id.lv_jugadores_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaJugadores
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
        idItemSeleccionado = idE_J.elementAt(id)
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
                eliminarJugador(idItemSeleccionado)
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

    fun eliminarJugador(
        idJugadorAeliminar: Int
    ){
        val listViewJugador = findViewById<ListView>(R.id.lv_jugadores_lista)

        var aux_E_J = arrayListOf<Equipo_Jugador>()

        BBaseDeDatosMemoria.arregloEquipos_Jugadores.forEach{ equipo_jugador:Equipo_Jugador ->
            if(idJugadorAeliminar != equipo_jugador.idEquipo_Jugador){
                aux_E_J.add(equipo_jugador)
            }
        }

        BBaseDeDatosMemoria.arregloEquipos_Jugadores = aux_E_J

        listaJugadores.removeAt(itemS)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaJugadores
        )
        listViewJugador.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}
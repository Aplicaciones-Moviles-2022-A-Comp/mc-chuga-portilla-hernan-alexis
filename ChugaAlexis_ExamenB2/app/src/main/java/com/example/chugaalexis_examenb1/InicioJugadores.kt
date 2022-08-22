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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioJugadores : AppCompatActivity() {
    var equipoSeleccionado=EquipoFutbol("","",0.0,0,"",0)
    val db = Firebase.firestore
    val equipos = db.collection("Equipos_Futbol")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<JugadorFutbol>?=null
    var jugadorSeleccionado:JugadorFutbol? = JugadorFutbol("","", "", "", 0.0, "", "")



    var resultAnadirJugador = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                equipoSeleccionado = intent.getParcelableExtra<EquipoFutbol>("PosEquipoFutbol")!!
            }
        }

    }

    var resultEditarJugador = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                equipoSeleccionado = intent.getParcelableExtra<EquipoFutbol>("PosEquipoFutbol")!!
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
        equipoSeleccionado = intent.getParcelableExtra<EquipoFutbol>("PosEquipoFutbol")!!
        listViewJugadores()
        val txtNombreEquipo=findViewById<TextView>(R.id.tv_nombreE_J)
        txtNombreEquipo.setText("Equipo: "+equipoSeleccionado.nombreEquipo)

        val btnCrearJugador = findViewById<Button>(R.id.btn_crear_jugador)
        btnCrearJugador.setOnClickListener {
            abrirActividadAddJugador(CrearJugadorFutbol::class.java)
        }

        val btnVolverJugador = findViewById<Button>(R.id.btn_volver_jugador)
        btnVolverJugador.setOnClickListener {
            val intentAtrasJugador = Intent(this, InicioEquipos::class.java)
            startActivity(intentAtrasJugador)
        }
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
        idItemSeleccionado = info.position
        Log.i("context-menu", "ID Jugador ${idItemSeleccionado}")
    }

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
    }
    fun listViewJugadores() {
        val equipoSubColeccion= equipos.document("${equipoSeleccionado.idEquipo}")
            .collection("Jugadores_Futbol")
            .whereEqualTo("idEquipo","${equipoSeleccionado.idEquipo}")

        val jugadores_lv = findViewById<ListView>(R.id.lv_jugadores_lista)
        equipoSubColeccion.get().addOnSuccessListener { result ->
            var listaJudadores = arrayListOf<JugadorFutbol>()
            for(document in result){
                listaJudadores.add(
                    JugadorFutbol(
                        document.id.toString(),
                        document.data.get("idEquipo").toString(),
                        document.data.get("nombre_jugador").toString(),
                        document.data.get("fechaNacimiento").toString(),
                        document.data.get("estatura").toString().toDouble(),
                        document.data.get("posicion").toString(),
                        document.data.get("esEcuatoriano").toString()
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaJudadores
            )
            jugadores_lv.adapter=adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(jugadores_lv)
        }
    }
    fun abrirActividadEditarJugador(
        clase: Class<*>
    ) {
        val intentEditarJugador = Intent(this, clase)
        intentEditarJugador.putExtra("jugador", jugadorSeleccionado)
        intentEditarJugador.putExtra("posicionEquipoeditar",equipoSeleccionado)
        resultEditarJugador.launch(intentEditarJugador)
    }

    fun abrirActividadAddJugador(
        clase: Class<*>
    ) {
        val intentAddNewJugador = Intent(this, clase)
        intentAddNewJugador.putExtra("posicionEquipo",equipoSeleccionado)
        resultAnadirJugador.launch(intentAddNewJugador)
    }


}
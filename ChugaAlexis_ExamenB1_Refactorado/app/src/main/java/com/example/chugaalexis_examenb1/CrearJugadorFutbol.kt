package com.example.chugaalexis_examenb1



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearJugadorFutbol : AppCompatActivity() {

    var nextIdJugador = 0
    var lastIdJugador = 0

    var nextIdE_J=0
    var lastIdE_J=0
    var idJugadorSeleccionado = 0
    var equipoPos = 0
    var idEquipoS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador_crear)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        equipoPos = intent.getIntExtra("posicionEquipo",-1)

        Log.i("posEquipo","${equipoPos}")
/*
        val jugadoresD = arrayListOf<String>()

        BBaseDeDatosMemoria.arregloJugadores.forEachIndexed{ indice: Int, jugador : JugadorFutbol ->

            jugadoresD.add(jugador.nombre.toString())
        }
*/


        EquipoBaseDeDatos.TablaEquipo!!.listarEquipos().forEachIndexed{ indice: Int, equipo : EquipoFutbol ->
            if (indice == equipoPos){
                idEquipoS = equipo.idEquipo
            }
        }


        var longitudListaJugador = EquipoBaseDeDatos.TablaEquipo!!.listarJugadores().lastIndex
        EquipoBaseDeDatos.TablaEquipo!!.listarJugadores().forEachIndexed{ indice: Int, jugador : JugadorFutbol ->
            Log.i("testExamen","${jugador.idJugador} -> ${jugador.nombre}")
            if (indice == longitudListaJugador){
                lastIdJugador = jugador.idJugador
            }
        }

        nextIdJugador = lastIdJugador+1


        var longE_J = Registros.arregloEquipos_Jugadores.lastIndex
        Registros.arregloEquipos_Jugadores.forEachIndexed{indice: Int, e_j:Equipo_Jugador ->
            if(indice==longE_J){
                lastIdE_J=e_j.idEquipo_Jugador
            }

        }
        nextIdE_J=lastIdE_J+1


        var txtNombre = findViewById<TextInputEditText>(R.id.txt_nombreJ_crear)
        var txtfechaJ = findViewById<TextInputEditText>(R.id.txt_fechaJ_crear)
        var txtEstaturaJ = findViewById<TextInputEditText>(R.id.txt_estaturaJ_crear)
        var txtPosicionJ= findViewById<TextInputEditText>(R.id.txt_posicionJ_crear)
        var txtEcuatorianoJ = findViewById<TextInputEditText>(R.id.txt_ecuatorianoJ_crear)

        var btnAddJugador= findViewById<Button>(R.id.btn_crear_jugador_)
        btnAddJugador.setOnClickListener {
            var nombre = txtNombre.text.toString()
            var fecha= txtfechaJ.text.toString()
            var estatura= txtEstaturaJ.text.toString()
            var posicion= txtPosicionJ.text.toString()
            var ecuatoriano= txtEcuatorianoJ.text.toString()
            Registros.arregloEquipos_Jugadores.add(
                Equipo_Jugador(nextIdE_J,idEquipoS,nextIdJugador)
            )
            EquipoBaseDeDatos.TablaEquipo!!.crearJugador(
                nextIdJugador,nombre,fecha,estatura,posicion,ecuatoriano)
            respuesta()
        }

        var btnCancelarJugador = findViewById<Button>(R.id.btn_cancelar_jugador_crear)
        btnCancelarJugador.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEquipo",equipoPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}
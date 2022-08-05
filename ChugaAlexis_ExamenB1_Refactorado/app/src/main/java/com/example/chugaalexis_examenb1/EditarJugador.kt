package com.example.chugaalexis_examenb1



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarJugador : AppCompatActivity() {

    var equipoPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador_editar)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val idEquipo_Jugador = intent.getIntExtra("jugador",1)
        equipoPos = intent.getIntExtra("posicionEquipoeditar",1)

        val txtNombreJ = findViewById<TextInputEditText>(R.id.txt_nombreJ_editar)
        val txtfechaJ = findViewById<TextInputEditText>(R.id.txt_fechaJ_editar)
        val txtestaturaJ = findViewById<TextInputEditText>(R.id.txt_estaturaJ_editar)
        val txtposcionJ = findViewById<TextInputEditText>(R.id.txt_posicionJ_editar)
        val txtecuatorianoJ = findViewById<TextInputEditText>(R.id.txt_ecuatorianoJ_crear)


        var idJugador: Int = 0

        BBaseDeDatosMemoria.arregloEquipos_Jugadores.forEachIndexed{ indice: Int, equipo_jugador : Equipo_Jugador ->
            if (idEquipo_Jugador == equipo_jugador.idEquipo_Jugador){
                txtNombreJ.setText(equipo_jugador.nombreE_J)
                idJugador = equipo_jugador.idJugador
            }
        }

        BBaseDeDatosMemoria.arregloJugadores.forEachIndexed{ indice: Int, jugador : JugadorFutbol ->
            if (idJugador == jugador.idJugador){
                txtNombreJ.setText(jugador.nombre)
                txtfechaJ.setText(jugador.fechaNacimiento)
                txtestaturaJ.setText(jugador.estatura.toString())
                txtposcionJ.setText(jugador.posicion)
                txtecuatorianoJ.setText(jugador.esEcuatoriano)
            }
        }

        val btnEditarJugador = findViewById<Button>(R.id.btn_editar_jugador)
        btnEditarJugador.setOnClickListener {
            BBaseDeDatosMemoria.arregloEquipos_Jugadores.forEachIndexed{ indice: Int, equipo_jugador: Equipo_Jugador ->
                if (idEquipo_Jugador == equipo_jugador.idEquipo_Jugador){
                    Log.i("editar","${txtNombreJ.text.toString()}")
                    equipo_jugador.nombreE_J = (txtNombreJ.text.toString())
                }
            }
            BBaseDeDatosMemoria.arregloJugadores.forEachIndexed{ indice: Int, jugador: JugadorFutbol ->
                if(idJugador==jugador.idJugador){
                    jugador.nombre=txtNombreJ.text.toString()
                    jugador.fechaNacimiento=txtfechaJ.text.toString()
                    jugador.estatura=txtestaturaJ.text.toString().toDouble()
                    jugador.posicion=txtposcionJ.text.toString()
                    jugador.esEcuatoriano=txtecuatorianoJ.text.toString()
                }

            }

            respuesta()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_jugador_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEquipoeditar",equipoPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}
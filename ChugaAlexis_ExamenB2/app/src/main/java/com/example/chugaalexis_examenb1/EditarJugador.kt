package com.example.chugaalexis_examenb1



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarJugador : AppCompatActivity() {
    var equipoSeleccionado = EquipoFutbol("", "", 0.0, 0, "", 0)
    var jugadorSeleccionado = JugadorFutbol("","", "", "", 0.0, "", "")
    val db = Firebase.firestore
    val equipos = db.collection("Equipos_Futbol")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador_editar)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()
        equipoSeleccionado = intent.getParcelableExtra<EquipoFutbol>("posicionEquipoeditar")!!
        jugadorSeleccionado = intent.getParcelableExtra<JugadorFutbol>("jugador")!!

        val txtNombreJ = findViewById<TextInputEditText>(R.id.txt_nombreJ_editar)
        val txtfechaJ = findViewById<TextInputEditText>(R.id.txt_fechaJ_editar)
        val txtestaturaJ = findViewById<TextInputEditText>(R.id.txt_estaturaJ_editar)
        val txtposcionJ = findViewById<TextInputEditText>(R.id.txt_posicionJ_editar)
        val txtecuatorianoJ = findViewById<TextInputEditText>(R.id.txt_ecuatorianoJ_crear)

        txtNombreJ.setText(jugadorSeleccionado.nombre)
        txtfechaJ.setText(jugadorSeleccionado.fechaNacimiento)
        txtestaturaJ.setText(jugadorSeleccionado.estatura.toString())
        txtposcionJ.setText(jugadorSeleccionado.posicion)
        txtecuatorianoJ.setText(jugadorSeleccionado.esEcuatoriano)

        val btnEditarJugador = findViewById<Button>(R.id.btn_editar_jugador)
        btnEditarJugador.setOnClickListener {
            equipos.document("${equipoSeleccionado.idEquipo}")
                .collection("Jugadores_Futbol")
                .document("${jugadorSeleccionado.idEquipo_J}")
                .update(
                    "nombre_jugador",txtNombreJ.text.toString(),
                    "fechaNacimiento",txtfechaJ.text.toString(),
                    "estatura",txtestaturaJ.text.toString(),
                    "posicion",txtposcionJ.text.toString(),
                    "esEcuatoriano",txtecuatorianoJ.text.toString()

                )
            Toast.makeText(this,"Jugador actualizado exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InicioJugadores::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_jugador_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEquipoeditar",equipoSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}
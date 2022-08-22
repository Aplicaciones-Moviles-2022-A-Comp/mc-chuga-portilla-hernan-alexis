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

class CrearJugadorFutbol : AppCompatActivity() {
    var equipoSeleccionado = EquipoFutbol("", "", 0.0, 0, "", 0)
    val db = Firebase.firestore
    val equipos = db.collection("Equipos_Futbol")
    val jugadores = db.collection("Jugadores_Futbol")
    var idJugadorSeleccionado = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador_crear)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        equipoSeleccionado = intent.getParcelableExtra<EquipoFutbol>("posicionEquipo")!!
        val equipoSubColeccion = equipos.document("${equipoSeleccionado.idEquipo}")
            .collection("Jugadores_Futbol")

        var txtNombre = findViewById<TextInputEditText>(R.id.txt_nombreJ_crear)
        var txtfechaJ = findViewById<TextInputEditText>(R.id.txt_fechaJ_crear)
        var txtEstaturaJ = findViewById<TextInputEditText>(R.id.txt_estaturaJ_crear)
        var txtPosicionJ = findViewById<TextInputEditText>(R.id.txt_posicionJ_crear)
        var txtEcuatorianoJ = findViewById<TextInputEditText>(R.id.txt_ecuatorianoJ_crear)

        Log.i("posEquipo", "${equipoSeleccionado.idEquipo}")

        var btnAddJugador = findViewById<Button>(R.id.btn_crear_jugador_)
        btnAddJugador.setOnClickListener {
            var jugador = hashMapOf(
                "idEquipo" to equipoSeleccionado.idEquipo,
                "nombre_jugador" to txtNombre.text.toString(),
                "fechaNacimiento" to txtfechaJ.text.toString(),
                "estatura" to txtEstaturaJ.text.toString(),
                "posicion" to txtPosicionJ.text.toString(),
                "esEcuatoriano" to txtEcuatorianoJ.text.toString()
            )
            equipoSubColeccion.add(jugador).addOnSuccessListener {
                Toast.makeText(this, "Jugador registrado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Jugador", "Con exito")
            }.addOnFailureListener {
                Log.i("Crear-Jugador", "Fallido")
            }

            val intentAddSucces = Intent(this, InicioJugadores::class.java)
            startActivity(intentAddSucces)
        }
        var btnCancelarJugador = findViewById<Button>(R.id.btn_cancelar_jugador_crear)
        btnCancelarJugador.setOnClickListener {
            respuesta()

        }
    }


    fun respuesta() {
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("PosEquipoFutbol", equipoSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}


package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarTareas : AppCompatActivity() {
    var catSeleccionado = Categoria("","","")
    var tareaSeleccionado = Tarea("","","","","")
    val db = Firebase.firestore
    val categorias = db.collection("Categorias_Proyecto")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tareas)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()
        catSeleccionado = intent.getParcelableExtra<Categoria>("posicionCateditar")!!
        tareaSeleccionado = intent.getParcelableExtra<Tarea>("tarea")!!

        val txtNombreJ = findViewById<EditText>(R.id.txt_titulo_editar_tarea)
        val txtfechaJ = findViewById<EditText>(R.id.txt_fecha_editar_tarea)
        val txtdescripcionJ = findViewById<EditText>(R.id.txt_descripcion_editar_tarea)


        txtNombreJ.setText(tareaSeleccionado.titulo)
        txtfechaJ.setText(tareaSeleccionado.descripcion)
        txtdescripcionJ.setText(tareaSeleccionado.fechaLimite)


        val btnEditarTarea = findViewById<Button>(R.id.btn_editar_tarea)
        btnEditarTarea.setOnClickListener {
            val txtNombreJ = findViewById<EditText>(R.id.txt_titulo_editar_tarea)
            val txtfechaJ = findViewById<EditText>(R.id.txt_fecha_editar_tarea)
            val txtdescripcionJ = findViewById<EditText>(R.id.txt_descripcion_editar_tarea)
            categorias.document("${catSeleccionado.idCategoria}")
                .collection("Tareas")
                .document("${tareaSeleccionado.idTarea}")
                .update(
                    "Titulo",txtNombreJ.text.toString(),
                    "Fecha Limite",txtfechaJ.text.toString(),
                    "Descripcion",txtdescripcionJ.text.toString(),
                    "IDCategoria",catSeleccionado.idCategoria
                )
            Toast.makeText(this,"Jugador actualizado exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InicioTareas::class.java)
            intentEditSucces.putExtra("PosCategoria",catSeleccionado)
            startActivity(intentEditSucces)
        }



    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEquipoeditar",catSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}
package com.example.movcomphacp2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CRUD_Entrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_entrenador)
        val botonCrearBDD= findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD
            .setOnClickListener {
                if(EBaseDeDatos.TablaEntrenador!=null){
                    val nombre=findViewById<EditText>(R.id.input_id)
                    val descripcion=findViewById<EditText>(R.id.input_descripcion)
                    EBaseDeDatos.TablaEntrenador!!.crearEntrenador(
                        nombre.text.toString(),
                        descripcion.text.toString()

                    )
                }
            }
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD
            .setOnClickListener {
                val id=findViewById<EditText>(R.id.input_id)
                val nombre=findViewById<EditText>(R.id.input_id)
                val descripcion=findViewById<EditText>(R.id.input_descripcion)
                val entrenador=EBaseDeDatos.TablaEntrenador!!.consultarUsuarioPorId(
                    id.text.toString().toInt()
                )
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre.toString())
                descripcion.setText(entrenador.descripcion .toString())
            }
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD
            .setOnClickListener {
                val id= findViewById<EditText>(R.id.input_id)
                val nombre=findViewById<EditText>(R.id.input_id)
                val descripcion=findViewById<EditText>(R.id.input_descripcion)
                EBaseDeDatos.TablaEntrenador!!.actualizarUsuarioFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    id.text.toString().toInt()
                )

            }
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD
            .setOnClickListener {
                val id= findViewById<EditText>(R.id.input_id)
                EBaseDeDatos.TablaEntrenador!!.eliminarUsuarioFormulario(
                    id.text.toString().toInt()
                )

            }
    }
}